package com.alumnus.zebra.utils

import org.junit.Test

import org.junit.Assert.*

class CalculatorTest {

    @Test
    fun calculateTSV() {
        assertEquals(9.81F, Calculator.calculateTSV(9.81, 0.0, 0.0))
        assertEquals(9.81F, Calculator.calculateTSV(0.0, 9.81, 0.0))
        assertEquals(9.81F, Calculator.calculateTSV(0.0, 0.0, 9.81))
    }
}