package com.alumnus.zebra.machineLearning

import android.util.Log
import com.alumnus.zebra.BuildConfig
import com.alumnus.zebra.pojo.AccelerationData
import com.alumnus.zebra.utils.DateFormatter
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException


const val EVENT_IMPACT = 1
const val EVENT_FREEFALL = 2

const val TYPE_UNKNOWN = 0
const val TYPE_FREEFALL_SIGNIFICANT = 1
const val TYPE_FREEFALL_INSIGNIFICANT = 2
const val TYPE_IMPACT_NEGLIGIBLE = 1
const val TYPE_IMPACT_SOFT = 2
const val TYPE_IMPACT_MEDIUM = 3
const val TYPE_IMPACT_HARD = 4
const val TYPE_IMPACT_FORCE = 5

const val TSV_IMPACT = 24.5
const val TSV_FREEFALL = 5.8
const val TSV_CALMZONE_HIGH = 11
const val TSV_CALMZONE_MEDIUM = 10
const val TSV_CALMZONE_LOW = 8
const val TSV_FREEFALL_SPIN = 4

const val DTSV_IMPACT_LOW = 10
const val DTSV_IMPACT_MEDIUM = 14.7
const val DTSV_IMPACT_HIGH = 20

const val interpolateFreq = 10
const val resampleFactor = 2

const val ZONE_PREFALL = 1
const val ZONE_FREEFALL = 2
const val ZONE_IMPACT = 3
const val ZONE_NOISE = 4
const val ZONE_CALM = 0

const val PREFALL_LENGTH = 200
const val PREIMPACT_LENGTH = 100
const val FREEFALL_SIGNIFICANT = 200
const val CALMZONE_DURATION = 30
const val IMPACT_LENGTH_MIN = 60
const val FORCE_AREA_MIN = 600
const val EVENT_GAP_MIN = 30


//var prefallData

class MachineLearning {
    private val TAG = "MachineLearning"

    /**
     *  3. Initialize algorithm parameters
     *  3.1. TSV
     *  3.2. DTSV
     *
     *  @param xyzList
     *  @return
     */
    private lateinit var mFileName: String

    fun CalculateTSV(xyzList: ArrayList<AccelerationData>, fileName: String? = DateFormatter.getTimeStampFileName(System.currentTimeMillis())): String {
        val TS = ArrayList<Long>()
        val TSV = ArrayList<Double>()
        val dTSV = ArrayList<Double>()
        for (xyz in xyzList) {
            TS.add(xyz.ts)
            TSV.add(Math.sqrt((xyz.x.toDouble() * xyz.x) + (xyz.y * xyz.y) + (xyz.z * xyz.z)))
        }
        dTSV.add(0.0)
        for (i in 1..TSV.size - 1) {
            dTSV.add(TSV[i] - TSV[i - 1])
        }
        if (fileName == null)
            mFileName = DateFormatter.getTimeStampFileName(System.currentTimeMillis())
        else
            mFileName = fileName
        return finalizeDetection(TS, TSV, dTSV)
        //return detectEvents(TS, TSV, dTSV)
    }

    /**
     *
     * @param ts
     * @param tsvDataset
     * @param dtsvDataset
     *
     * @return
     */
    fun detectEvents(ts: ArrayList<Long>, tsvDataset: ArrayList<Double>, dtsvDataset: ArrayList<Double>): DetectPlusNoise {
        val noiseZones: ArrayList<NoiseZone> = ArrayList()
        val detectedEvents = arrayListOf<DetectedEvent>()
        val numberOfSamples = ts.size

        var maxTsv = -1.0
        var minTsv = -1.0
        var freefallStart = -1
        var impactStart = -1
        var noiseStart = -1
        var calmZoneCount = 0
        var spinDetected: Boolean
        var impactType: Int
        var maxDtsv: Double
        var areaUnderCurve: Double
        for (i in 0..numberOfSamples - 1) {
            val currentTsv = tsvDataset[i]
            // Update max / min TSV values if required
            if (maxTsv >= 0) {
                if (maxTsv < currentTsv) {
                    maxTsv = currentTsv
                }
            } else if (minTsv >= 0) {
                if (minTsv > currentTsv) {
                    minTsv = currentTsv
                }
            }
            if (currentTsv > TSV_IMPACT) {
                // Impact zone
                if (noiseStart >= 0) {
                    calmZoneCount = 0
                }
                // Finalize stuff if this marks the end of a freefall event
                if (freefallStart > 0) {
                    spinDetected = detectSpin(tsvDataset, freefallStart, i)
                    detectedEvents.add(DetectedEvent(EVENT_FREEFALL, freefallStart, i, minTsv, spinDetected))
                    freefallStart = -1
                    minTsv = -1.0
                }
                // Initialize stuff if this is the start of impact
                if (impactStart < 0) {
                    impactStart = i
                    maxTsv = currentTsv
                }
            } else if (currentTsv < TSV_FREEFALL) {
                // Freefall zone
                if (noiseStart >= 0) {
                    calmZoneCount = 0
                }
                // Finalize stuff if this marks the end of an impact event
                if (impactStart > 0) {
                    // Look at DTSV to determine type
                    var maxDtsv = -1.0
                    for (j in impactStart..i) {
                        if (maxDtsv < dtsvDataset[j]) {
                            maxDtsv = dtsvDataset[j]
                        }
                    }
                    if (maxDtsv >= DTSV_IMPACT_HIGH) {
                        // If the DTSV is too highm it is an impact
                        impactType = TYPE_IMPACT_HARD
                    } else {
                        // Otherwise, it might be force impartion or impact
                        //areaUnderCurve = simps(tsvDataset[impactStart:i], dx = timeDiffInMs)
                        areaUnderCurve = SimpsonsRule.integrate(tsvDataset, impactStart, i, 1)
                        appendLog("Area under curve:" + areaUnderCurve)
                        print("Area under curve:" + areaUnderCurve)
                        if (areaUnderCurve >= FORCE_AREA_MIN) {
                            // Not actually an impact, just external application of force
                            impactType = TYPE_IMPACT_FORCE
                        } else {
                            // Impact it is
                            if (maxDtsv >= DTSV_IMPACT_MEDIUM) {
                                impactType = TYPE_IMPACT_MEDIUM
                            } else if (maxDtsv >= DTSV_IMPACT_LOW) {
                                impactType = TYPE_IMPACT_SOFT
                            } else {
                                impactType = TYPE_IMPACT_NEGLIGIBLE
                            }
                        }
                    }
                    detectedEvents.add(DetectedEvent(EVENT_IMPACT, impactStart, i, maxTsv, maxDtsv, impactType))

                    // Noise filtering to be done only for high and medium impacts
                    if ((impactType == TYPE_IMPACT_HARD) or (impactType == TYPE_IMPACT_MEDIUM)) {
                        // End of impact, detect noise zone
                        if (noiseStart < 0) {
                            noiseStart = i
                        }
                        calmZoneCount = 0
                    }
                    impactStart = -1
                    maxTsv = -1.0
                }
                // Initialize stuff if this is the start of freefall
                if (freefallStart < 0) {
                    freefallStart = i
                    minTsv = currentTsv
                }
            } else {
                // Regular zone
                // Check for end of noise
                if (noiseStart >= 0) {
                    if ((currentTsv <= TSV_CALMZONE_HIGH) and (currentTsv >= TSV_CALMZONE_LOW)) {
                        calmZoneCount += 1
                        if (calmZoneCount >= CALMZONE_DURATION) {
                            // We have calmed down enough
                            noiseZones.add(NoiseZone(noiseStart, i))
                            noiseStart = -1
                            calmZoneCount = 0
                        }
                    } else {
                        // Still some noise
                        calmZoneCount = 0
                    }
                }
                // Finalize stuff if this marks the end of an impact event
                if (impactStart > 0) {
                    // Look at DTSV to determine type
                    maxDtsv = -1.0
                    for (j in impactStart..i) {
                        if (maxDtsv < dtsvDataset[j]) {
                            maxDtsv = dtsvDataset[j]
                        }
                    }
                    if (maxDtsv >= DTSV_IMPACT_HIGH) {
                        // If the DTSV is too high, it is an impact
                        impactType = TYPE_IMPACT_HARD
                    } else {
                        //areaUnderCurve = simps(tsvDataset[impactStart:i], dx = timeDiffInMs)
                        areaUnderCurve = SimpsonsRule.integrate(tsvDataset, impactStart, i, 1)

                        appendLog("Area under curve: " + areaUnderCurve)
                        print("Area under curve: " + areaUnderCurve)
                        if (areaUnderCurve >= FORCE_AREA_MIN) {
                            // Not actually an impact, just external application of force
                            impactType = TYPE_IMPACT_FORCE
                        } else {
                            // Impact it is
                            if (maxDtsv >= DTSV_IMPACT_MEDIUM) {
                                impactType = TYPE_IMPACT_MEDIUM
                            } else if (maxDtsv >= DTSV_IMPACT_LOW) {
                                impactType = TYPE_IMPACT_SOFT
                            } else {
                                impactType = TYPE_IMPACT_NEGLIGIBLE
                            }
                        }
                    }
                    detectedEvents.add(DetectedEvent(EVENT_IMPACT, impactStart, i, maxTsv, maxDtsv, impactType))

                    // Noise filtering to be done only for high and medium impacts
                    if ((impactType == TYPE_IMPACT_HARD) or (impactType == TYPE_IMPACT_MEDIUM)) {
                        // End of impact, detect noise zone
                        if (noiseStart < 0) {
                            noiseStart = i
                        }
                        calmZoneCount = 0
                    }
                    impactStart = -1
                    maxTsv = -1.0
                }
                // Finalize stuff if this marks the end of a freefall event
                if (freefallStart > 0) {
                    spinDetected = detectSpin(tsvDataset, freefallStart, i)
                    detectedEvents.add(DetectedEvent(EVENT_FREEFALL, freefallStart, i, minTsv, spinDetected))
                    freefallStart = -1
                    minTsv = -1.0
                }
            }
        }
        Log.d(TAG, "Completed")
        for (i in 0..detectedEvents.size - 1) {
            Log.e(TAG, "" + detectedEvents[i].toString())
        }
        return DetectPlusNoise(detectedEvents, noiseZones)
    }

    /**
     * Input:
     *  (1: list) TSV dataset
     *  (2: int) Freefall start index
     *  (3: int) Freefall end index
     * Output:
     *  (1: Bool) True if spin detected, False otherwise
     */
    fun detectSpin(tsvDataset: ArrayList<Double>, start: Int, end: Int): Boolean {
        var detected = false
        for (i in start..end) {
            if (tsvDataset[i] >= TSV_FREEFALL_SPIN) {
                detected = true
                break
            }
        }
        return detected
    }


    /**
     * Summarize event detection process
     */
    private fun finalizeDetection(ts: ArrayList<Long>, tsvDataset: ArrayList<Double>, dtsvDataset: ArrayList<Double>): String {

        val detectPlusNoise: DetectPlusNoise = detectEvents(ts, tsvDataset, dtsvDataset)
        val events: ArrayList<DetectedEvent> = detectPlusNoise.detectedEvents
        val noises: ArrayList<NoiseZone> = detectPlusNoise.noiseZones
        appendLog("Detected events:")
        print("Detected events:")
        parseEvents(detectPlusNoise.detectedEvents, ts)

        appendLog("Noise zones:")
        print("Noise zones:")
        for (noiseZone in noises) {
            appendLog(noiseZone.toString())
            print(noiseZone)
        }

        //else:
        //filteredEvents = events

        // 6.Find type of abuse
        // 6.1.Find first significant fall event

        // Only use significant events
        var numberOfSignificantFalls = 0
        var numberOfSignificantImpacts = 0
        var numberOfForces = 0
        var firstFall = 1
        var prefallFound = false
        var preimpactFound = false

        var preimpactData = arrayListOf<Int>()
        var prefallDataTs = arrayListOf<Long>()
        var preimpactDataTs = arrayListOf<Int>()
        var lastEventEnded = 0

        var freefallData = IntArray(ts.size)//arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0) //[0] * numberOfResampledSamples
        var impactData = IntArray(ts.size)//arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0) //[0] * numberOfResampledSamples

        for (event in events) {
            if ((event.event_type == EVENT_FREEFALL) and ((ts[event.count] - ts[event.eventStart]) >= FREEFALL_SIGNIFICANT)) {
                if (firstFall > 0) {
                    prefallFound = true
                    // prefallData = [Math.max((event.eventStart - PREFALL_LENGTH), 0), event.eventStart]
                    //for (i in range(max([event.eventStart - PREFALL_LENGTH, 0]), event.eventStart)) {
                    //    prefallDataTs.add(ts[i])
                    //}
                    firstFall = 0
                }
                numberOfSignificantFalls += 1
                freefallData[event.eventStart] = 100
                lastEventEnded = event.count
            } else if ((event.event_type == EVENT_IMPACT) and (event.impactType == TYPE_IMPACT_FORCE)) {
                numberOfForces += 1
            } else if ((event.event_type == EVENT_IMPACT) and (event.impactType >= TYPE_IMPACT_MEDIUM)) {
                numberOfSignificantImpacts += 1
                impactData[event.eventStart] = 100
                if ((event.eventStart - lastEventEnded) >= PREIMPACT_LENGTH) {
                    // preimpactData.add(max([event.eventStart - PREIMPACT_LENGTH, lastEventEnded + 1]), event.eventStart)
                    //preimpactDataTs.add([ts[max(event.eventStart - PREIMPACT_LENGTH, lastEventEnded + 1)], ts[event.eventStart])
                    preimpactFound = true
                }
                lastEventEnded = event.count
            }
        }
        appendLog("Significant freefall events: $numberOfSignificantFalls")
        print("Significant freefall events: $numberOfSignificantFalls")
        appendLog("Significant impact events: $numberOfSignificantImpacts")
        print("Significant impact events: $numberOfSignificantImpacts")
        appendLog("Force impartions: $numberOfForces")
        print("Force impartions: $numberOfForces")

        return "Significant freefall events: $numberOfSignificantFalls , Significant impact events: $numberOfSignificantImpacts ,Force impartions: $numberOfForces"
    }


    /**
     * Parse Events
     */
    fun parseEvents(eventList: ArrayList<DetectedEvent>, tsDataset: ArrayList<Long>) {
        var spinResult: String
        var impactType: String
        if (BuildConfig.DEBUG) {
            for (event in eventList) {
                appendLog(event.toString())
                print(event)
            }
        }
        var lastEvent = 0
        for (event in eventList) {
            if (event.event_type == EVENT_FREEFALL) {
                if (event.spinDetected) {
                    spinResult = "Yes"
                } else {
                    spinResult = "No"
                }
                appendLog("After ${(event.eventStart - lastEvent)} ms: Freefall of duration ${(tsDataset[event.count] - tsDataset[event.event_type])} ms, minimum TSV: ${(event.minTsv)} m/s2, estimated fall: ${estimateDistance((tsDataset[event.count] - tsDataset[event.eventStart]).toDouble())} feet, spin detected: $spinResult")
                print("After ${(event.eventStart - lastEvent)} ms: Freefall of duration ${(tsDataset[event.count] - tsDataset[event.event_type])} ms, minimum TSV: ${(event.minTsv)} m/s2, estimated fall: ${estimateDistance((tsDataset[event.count] - tsDataset[event.eventStart]).toDouble())} feet, spin detected: $spinResult")
            } else if (event.event_type == EVENT_IMPACT) {
                if (event.impactType == TYPE_IMPACT_HARD) {
                    impactType = "Severe"
                } else if (event.impactType == TYPE_IMPACT_MEDIUM) {
                    impactType = "Medium"
                } else if (event.impactType == TYPE_IMPACT_SOFT) {
                    impactType = "Low"
                } else if (event.impactType == TYPE_IMPACT_FORCE) {
                    impactType = "Force"
                } else {
                    impactType = "Negligible"
                }
                appendLog("After ${(event.eventStart - lastEvent)} ms: Impact of duration ${(tsDataset[event.count] - tsDataset[event.eventStart])}, ms maximum TSV: ${(event.maxTsv)} m/s2, maximum DTSV: ${event.dTsv}, type: $impactType")
                print("After ${(event.eventStart - lastEvent)} ms: Impact of duration ${(tsDataset[event.count] - tsDataset[event.eventStart])}, ms maximum TSV: ${(event.maxTsv)} m/s2, maximum DTSV: ${event.dTsv}, type: $impactType")
                //appendLog("Impact direction =", detectImpactDirection(tsvResampled, xResampled, yResampled, zResampled, event.eventStart, event.count))
                //print("Impact direction =", detectImpactDirection(tsvResampled, xResampled, yResampled, zResampled, event[1], event[2]))
            } else {
                appendLog("After ${(event.eventStart - lastEvent)} ms: Unknown event of duration ${(tsDataset[event.count] - tsDataset[event.eventStart])} ms")
                print("After ${(event.eventStart - lastEvent)} ms: Unknown event of duration ${(tsDataset[event.count] - tsDataset[event.eventStart])} ms")
            }
            lastEvent = event.count - 1
        }
        return
    }

    fun estimateDistance(durarion: Double): Double {
        return Math.round((3.28 * (9.81 * (durarion / 1000) * (durarion / 1000)) / 2) * 1.225).toDouble()
    }


    /**
     *
     */
    fun appendLog(text: String?) {

        val logFile = File("sdcard/log-$mFileName.txt")
        if (!logFile.exists()) {
            try {
                logFile.createNewFile()
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
        }
        try {
            //BufferedWriter for performance, true to set append to file flag
            val buf = BufferedWriter(FileWriter(logFile, true))
            buf.append(text)
            buf.newLine()
            buf.close()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    /**
     * TODO detectImpactDirection
     * Required xyz
     */
    /*fun detectImpactDirection(tsvDataset: Double, xDataset: Float, yDataset: Float, zDataset: Float, start: Int, end: Int) {
        var xComponent = 0
        var yComponent = 0
        var zComponent = 0

        var maxTsv = -1
        var maxI = -1

        for (i in start..end) {
            if (tsvDataset.get(i) > maxTsv) {
                maxTsv = tsvDataset.get(i)
                maxI = i
            }
        }

        if (maxI >= 0) {

            //# Values
            xComponent = Math.round((xDataset[maxI] * xDataset[maxI] * 100) / (tsvDataset[maxI] * tsvDataset[maxI]), 2)
            yComponent = Math.round((yDataset[maxI] * yDataset[maxI] * 100) / (tsvDataset[maxI] * tsvDataset[maxI]), 2)
            zComponent = Math.round((zDataset[maxI] * zDataset[maxI] * 100) / (tsvDataset[maxI] * tsvDataset[maxI]), 2)

            //# Signs
            if (xDataset[maxI] < 0)
                xComponent = -xComponent

            if (yDataset[maxI] < 0)
                yComponent = -yComponent

            if (zDataset[maxI] < 0)
                zComponent = -zComponent
        }
        return [xComponent, yComponent, zComponent]
    }*/

}
