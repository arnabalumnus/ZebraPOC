package com.alumnus.zebra.machineLearning.utils

/**
 * @author Arnab Kundu
 */
object DataAnalyzer {

    const val FREE_FALL = 0.0
    const val FREE_FALL_THRESHOLD = 5.0 //  TODO set proper value to this variable after trial

    const val GRAVITY = 9.81
    const val GRAVITY_THRESHOLD = 11.0 // TODO set proper value to this variable after trial

    /**
     * Checks for provided data set has actual Event or not
     * @param tsvList   Takes list of TSV values as parameter
     * @return          true or false
     */
    fun hasEvent(tsvList: ArrayList<Double>): Boolean {

        // Assuming it's a Throw on satisfying if condition
        for (tsv: Double in tsvList) {
            if (tsv > GRAVITY_THRESHOLD) {
                return true
            }
        }

        // Assuming its a FreeFall on satisfying if condition
        var count = 0
        for (tsv: Double in tsvList) {
            if (tsv < FREE_FALL_THRESHOLD) {
                count++
                // TODO
                //  Set proper value in place of 5 after trial.
                //  Single TSV value less than 5 is not enough to say its FreeFall.
                //  Need couple of consecutive TSV value less than FREE_FALL_THRESHOLD to confirm
                //  about a FreeFall
                if (count >= 5) {
                    return true
                }
            } else {
                count = 0
            }
        }
        // If none of the above conditions satisfy. Then return false
        return false
    }
}