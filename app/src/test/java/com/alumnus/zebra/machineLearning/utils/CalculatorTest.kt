package com.alumnus.zebra.machineLearning.utils

import org.junit.Test

import org.junit.Assert.*

class CalculatorTest {

    @Test
    fun calculateTSV() {
        assertEquals("Error in TSV calculation", 9.81, Calculator.calculateTSV(9.81, 0.0, 0.0), 0.0)
        assertEquals("Error in TSV calculation", 9.81, Calculator.calculateTSV(0.0, 9.81, 0.0), 0.0)
        assertEquals("Error in TSV calculation", 9.81, Calculator.calculateTSV(0.0, 0.0, 9.81), 0.0)
    }

    @Test
    fun calculateDTSV() {
    }
}