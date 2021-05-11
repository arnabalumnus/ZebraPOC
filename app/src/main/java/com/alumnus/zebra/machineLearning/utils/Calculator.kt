package com.alumnus.zebra.machineLearning.utils


/**
 * @author Arnab Kundu
 */
object Calculator {


    /**
     * Calculates TSV from X-Y-Z axis data of accelerometer
     *
     * @param x     X-Axis value of accelerometer's data
     * @param y     Y-Axis value of accelerometer's data
     * @param z     Z-Axis value of accelerometer's data
     * @return      TSV stands for SquareRootOf  _______________
     *                                         √( X² + Y² + Z² )
     */
    fun calculateTSV(x: Double, y: Double, z: Double): Double {
        return Math.sqrt(x * x + y * y + z * z)
    }


    /**
     * Calculates dTSV from TSV
     * @param tsv   A TSV value
     * @param tsv1  Next consecutive TSV value with respect to TimeStamp
     * @return dTSV stand for delta TSV
     */
    fun calculateDTSV(tsv: Double, tsv1: Double): Double {
        return tsv1 - tsv
    }


}