package com.alumnus.zebra.utils

object Calculator {

    fun calculateTSV(x: Double, y: Double, z: Double): Float {
        return Math.sqrt(x * x + y * y + z * z).toFloat()
    }
}