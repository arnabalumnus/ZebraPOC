package com.alumnus.zebra.machineLearning

import android.util.Log
import com.alumnus.zebra.pojo.AccelerationData


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
    fun CalculateTSV(xyzList: ArrayList<AccelerationData>): DetectPlusNoise {
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
        return detectEvents(TS, TSV, dTSV)
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
                        //print("Area under curve:", areaUnderCurve)
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

                        //print("Area under curve:", areaUnderCurve)
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
}