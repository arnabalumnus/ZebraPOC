package com.alumnus.zebra.machineLearning

import android.content.Context
import com.alumnus.zebra.BuildConfig
import com.alumnus.zebra.machineLearning.pojo.DetectPlusNoise
import com.alumnus.zebra.machineLearning.pojo.DetectedEvent
import com.alumnus.zebra.machineLearning.pojo.NoiseZone
import com.alumnus.zebra.machineLearning.utils.Calculator
import com.alumnus.zebra.machineLearning.utils.Calculator.estimateDistance
import com.alumnus.zebra.machineLearning.utils.LogFileGenerator.appendLog
import com.alumnus.zebra.machineLearning.utils.SimpsonsRule
import com.alumnus.zebra.pojo.AccelerationNumericData
import com.alumnus.zebra.utils.DateFormatter


//var prefallData TODO convert from python code

/**
 * This class does whole data analysis part on top of accelerometer data
 * and Generate logs for respective data.
 *
 * @author Converted code from pre-existing Python script
 */
class DataAnalysis {

    //region Constants
    val EVENT_IMPACT = 1
    val EVENT_FREEFALL = 2
    val TYPE_UNKNOWN = 0
    val TYPE_FREEFALL_SIGNIFICANT = 1
    val TYPE_FREEFALL_INSIGNIFICANT = 2
    val TYPE_IMPACT_NEGLIGIBLE = 1
    val TYPE_IMPACT_SOFT = 2
    val TYPE_IMPACT_MEDIUM = 3
    val TYPE_IMPACT_HARD = 4
    val TYPE_IMPACT_FORCE = 5
    val TSV_IMPACT = 24.5
    val TSV_FREEFALL = 5.8
    val TSV_CALMZONE_HIGH = 11
    val TSV_CALMZONE_MEDIUM = 10
    val TSV_CALMZONE_LOW = 8
    val TSV_FREEFALL_SPIN = 4
    val DTSV_IMPACT_LOW = 10
    val DTSV_IMPACT_MEDIUM = 14.7
    val DTSV_IMPACT_HIGH = 20
    val interpolateFreq = 10
    val resampleFactor = 2
    val ZONE_PREFALL = 1
    val ZONE_FREEFALL = 2
    val ZONE_IMPACT = 3
    val ZONE_NOISE = 4
    val ZONE_CALM = 0
    val PREFALL_LENGTH = 200
    val PREIMPACT_LENGTH = 100
    val FREEFALL_SIGNIFICANT = 200
    val CALMZONE_DURATION = 30
    val IMPACT_LENGTH_MIN = 60
    val FORCE_AREA_MIN = 600
    val EVENT_GAP_MIN = 30

    //endregion

    private lateinit var mFileName: String
    private lateinit var context: Context
    private lateinit var xyzList: ArrayList<AccelerationNumericData>
    private lateinit var TSV: ArrayList<Double>

    /**
     * Starts the long process of event analysis.
     * 1. Calculate TSV (Total Sum Vector)
     * @see Calculator.calculateTSV
     * 2. Calculate DTSV (Deference between constitutive two TSV value)
     * @see Calculator.calculateDTSV
     * 3. Performs data analysis with TSV and dTSV data sets for event detection
     * @see finalizeDetection
     *
     * @param xyzList       List of xyz axis value of accelerometer
     * @param context       Context contains android background information. Needed to create file.
     * @param fileName      File will generate with provided filename
     * @return              finalizeDetection()
     */
    fun startEventAnalysis(xyzList: ArrayList<AccelerationNumericData>, context: Context, fileName: String? = DateFormatter.getTimeStampFileName(System.currentTimeMillis())): String {
        val tsList = ArrayList<Long>()
        val tsvList = ArrayList<Double>()
        val dtsvList = ArrayList<Double>()
        for (xyz in xyzList) {
            tsList.add(xyz.ts)
            tsvList.add(Calculator.calculateTSV(x = xyz.x.toDouble(), y = xyz.y.toDouble(), z = xyz.z.toDouble()))
        }
        dtsvList.add(0.0)
        for (i in 1 until tsvList.size) {
            dtsvList.add(Calculator.calculateDTSV(tsv = tsvList[i], tsv1 = tsvList[i - 1]))
        }
        this.context = context
        if (fileName == null)
            mFileName = DateFormatter.getTimeStampFileName(System.currentTimeMillis())
        else
            mFileName = fileName
        this.xyzList = xyzList
        this.TSV = tsvList
        return finalizeDetection(tsList, tsvList, dtsvList)
    }


    /**
     * Detect Events and Noise on provided data set and Returns combine object of Event & Noise
     *
     * @param ts            List of Timestamp
     * @param tsvDataSet    List of TSV data set
     * @param dtsvDataSet   List of DTSV data set
     * @return Event & Noise object
     */
    private fun detectEvents(ts: ArrayList<Long>, tsvDataSet: ArrayList<Double>, dtsvDataSet: ArrayList<Double>): DetectPlusNoise {
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
            val currentTsv = tsvDataSet[i]
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
                    spinDetected = detectSpin(tsvDataSet, freefallStart, i)
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
                        if (maxDtsv < dtsvDataSet[j]) {
                            maxDtsv = dtsvDataSet[j]
                        }
                    }
                    if (maxDtsv >= DTSV_IMPACT_HIGH) {
                        // If the DTSV is too highm it is an impact
                        impactType = TYPE_IMPACT_HARD
                    } else {
                        // Otherwise, it might be force impartion or impact
                        //areaUnderCurve = simps(tsvDataset[impactStart:i], dx = timeDiffInMs)
                        areaUnderCurve = SimpsonsRule.integrate(tsvDataSet, impactStart, i, 1)
                        appendLog(context, mFileName, "Area under curve:" + areaUnderCurve)
                        println("Area under curve:" + areaUnderCurve)
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
                        if (maxDtsv < dtsvDataSet[j]) {
                            maxDtsv = dtsvDataSet[j]
                        }
                    }
                    if (maxDtsv >= DTSV_IMPACT_HIGH) {
                        // If the DTSV is too high, it is an impact
                        impactType = TYPE_IMPACT_HARD
                    } else {
                        //areaUnderCurve = simps(tsvDataset[impactStart:i], dx = timeDiffInMs)
                        areaUnderCurve = SimpsonsRule.integrate(tsvDataSet, impactStart, i, 1)

                        appendLog(context, mFileName, "Area under curve: " + areaUnderCurve)
                        println("Area under curve: " + areaUnderCurve)
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
                    spinDetected = detectSpin(tsvDataSet, freefallStart, i)
                    detectedEvents.add(DetectedEvent(EVENT_FREEFALL, freefallStart, i, minTsv, spinDetected))
                    freefallStart = -1
                    minTsv = -1.0
                }
            }
        }
        for (i in 0..detectedEvents.size - 1) {
            println(detectedEvents[i].toString())
        }
        return DetectPlusNoise(detectedEvents, noiseZones)
    }


    /**
     *  Detect whether device spinning at the point of impact event
     *
     *  @param tsvDataSet   List of TSV data set
     *  @param start        Free fall start index
     *  @param end          Free fall end index
     *  @return             True if spin detected, False otherwise
     */
    private fun detectSpin(tsvDataSet: ArrayList<Double>, start: Int, end: Int): Boolean {
        var detected = false
        for (i in start..end) {
            if (tsvDataSet[i] >= TSV_FREEFALL_SPIN) {
                detected = true
                break
            }
        }
        return detected
    }


    /**
     * Collate all events and noise zones
     *
     * @param ts            List of Timestamp
     * @param tsvDataSet    List of TSV data set
     * @param dtsvDataSet   List of dTSV data set
     *
     * @return              A string message that contains
     *                      1. Significant free fall events:
     *                      2. Significant impact events:
     *                      3. Force impartions: "
     */
    private fun finalizeDetection(ts: ArrayList<Long>, tsvDataSet: ArrayList<Double>, dtsvDataSet: ArrayList<Double>): String {

        val detectPlusNoise: DetectPlusNoise = detectEvents(ts, tsvDataSet, dtsvDataSet)
        val events: ArrayList<DetectedEvent> = detectPlusNoise.detectedEvents
        val noises: ArrayList<NoiseZone> = detectPlusNoise.noiseZones
        appendLog(context, mFileName, "Detected events:")
        println("Detected events:")
        parseEvents(detectPlusNoise.detectedEvents, ts)

        appendLog(context, mFileName, "Noise zones:")
        println("Noise zones:")
        for (noiseZone in noises) {
            appendLog(context, mFileName, noiseZone.toString())
            println(noiseZone)
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
        appendLog(context, mFileName, "Significant freefall events: $numberOfSignificantFalls")
        println("Significant freefall events: $numberOfSignificantFalls")
        appendLog(context, mFileName, "Significant impact events: $numberOfSignificantImpacts")
        println("Significant impact events: $numberOfSignificantImpacts")
        appendLog(context, mFileName, "Force impartions: $numberOfForces")
        println("Force impartions: $numberOfForces")

        return "Significant freefall events: $numberOfSignificantFalls , Significant impact events: $numberOfSignificantImpacts ,Force impartions: $numberOfForces"
    }


    /**
     * Parse Events and generate logs according to event data
     *
     * @param eventList     List of Detected events
     * @param tsDataSet     List of Timestamp data set
     */
    private fun parseEvents(eventList: ArrayList<DetectedEvent>, tsDataSet: ArrayList<Long>) {
        var spinResult: String
        var impactType: String
        if (BuildConfig.DEBUG) {
            for (event in eventList) {
                appendLog(context, mFileName, event.toString())
                println(event)
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
                appendLog(context, mFileName, "After ${(event.eventStart - lastEvent)} ms: Freefall of duration ${(tsDataSet[event.count] - tsDataSet[event.event_type])} ms, minimum TSV: ${(event.minTsv)} m/s2, estimated fall: ${estimateDistance((tsDataSet[event.count] - tsDataSet[event.eventStart]).toDouble())} feet, spin detected: $spinResult")
                println("After ${(event.eventStart - lastEvent)} ms: Freefall of duration ${(tsDataSet[event.count] - tsDataSet[event.event_type])} ms, minimum TSV: ${(event.minTsv)} m/s2, estimated fall: ${estimateDistance((tsDataSet[event.count] - tsDataSet[event.eventStart]).toDouble())} feet, spin detected: $spinResult")
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
                appendLog(context, mFileName, "After ${(event.eventStart - lastEvent)} ms: Impact of duration ${(tsDataSet[event.count] - tsDataSet[event.eventStart])}, ms maximum TSV: ${(event.maxTsv)} m/s2, maximum DTSV: ${event.dTsv}, type: $impactType")
                println("After ${(event.eventStart - lastEvent)} ms: Impact of duration ${(tsDataSet[event.count] - tsDataSet[event.eventStart])}, ms maximum TSV: ${(event.maxTsv)} m/s2, maximum DTSV: ${event.dTsv}, type: $impactType")

                appendLog(context, mFileName, detectImpactDirection(TSV, event.eventStart, event.count - 1))
                //println("${detectImpactDirection(TSV, event.eventStart, event.count - 1)}")
            } else {
                appendLog(context, mFileName, "After ${(event.eventStart - lastEvent)} ms: Unknown event of duration ${(tsDataSet[event.count] - tsDataSet[event.eventStart])} ms")
                println("After ${(event.eventStart - lastEvent)} ms: Unknown event of duration ${(tsDataSet[event.count] - tsDataSet[event.eventStart])} ms")
            }
            lastEvent = event.count - 1
        }
        return
    }


    /**
     * Impact direction of the specified event between start and end point
     *
     * @param tsvDataSet    List of TSV data set calculated from x,y & z axis value
     * @param start         Event starting point
     * @param end           Event ending point
     * @return              Returns Impact direction of the event to log into logfile
     */
    // TODO Required xyz as params
    private fun detectImpactDirection(tsvDataSet: ArrayList<Double>, start: Int, end: Int): String {
        var xComponent = 0L
        var yComponent = 0L
        var zComponent = 0L

        var maxTsv = -1.0
        var maxI = -1

        for (i in start..end) {
            if (tsvDataSet.get(i) > maxTsv) {
                maxTsv = tsvDataSet.get(i)
                maxI = i
            }
        }

        if (maxI >= 0) {
            // Values
            xComponent = Math.round((xyzList.get(maxI).x * xyzList.get(maxI).x * 100) / (tsvDataSet.get(maxI) * tsvDataSet.get(maxI)))
            yComponent = Math.round((xyzList.get(maxI).y * xyzList.get(maxI).y * 100) / (tsvDataSet.get(maxI) * tsvDataSet.get(maxI)))
            zComponent = Math.round((xyzList.get(maxI).z * xyzList.get(maxI).z * 100) / (tsvDataSet.get(maxI) * tsvDataSet.get(maxI)))

            //# Signs
            if (xyzList.get(maxI).x < 0) {
                xComponent = -xComponent
            }

            if (xyzList.get(maxI).y < 0) {
                yComponent = -yComponent
            }

            if (xyzList.get(maxI).z < 0) {
                zComponent = -zComponent
            }
        }
        return "Impact direction = [$xComponent, $yComponent, $zComponent]"
    }
}
