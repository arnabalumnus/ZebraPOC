package com.alumnus.zebra.utils

import org.junit.Test

import org.junit.Assert.*

class DataAnalyzerTest {

    @Test
    fun hasThrowEvent() {
        assertEquals(true, DataAnalyzer.hasEvent(arrayListOf(11.0, 0.0, 52.0, 36.0)))
    }


    @Test
    fun hasFreeFallEvent() {
        assertEquals(true, DataAnalyzer.hasEvent(arrayListOf(9.81, 4.0, 3.0, 2.0, 1.0, 0.0, 9.81)))
    }


    @Test
    fun hasNoEvent() {
        assertEquals(false, DataAnalyzer.hasEvent(arrayListOf(9.81, 9.8, 9.0, 8.5, 7.5, 9.0, 10.0)))
    }
}