package com.simple.weather

import com.simple.weather.util.MoonPhaseConverter
import org.junit.Assert
import org.junit.Test

class MoonPhaseConverterTest {

    @Test
    fun convertPhaseToDescription_0_newMoon() {
        Assert.assertEquals("New\nMoon", MoonPhaseConverter.convertPhaseToDescription(0.0))
    }

    @Test
    fun convertPhaseToDescription_0point1_waxingCrescent() {
        Assert.assertEquals("Waxing\nCrescent", MoonPhaseConverter.convertPhaseToDescription(0.1))
    }

    @Test
    fun convertPhaseToDescription_0point25_firstQuarter() {
        Assert.assertEquals("First\nQuarter", MoonPhaseConverter.convertPhaseToDescription(0.25))
    }

    @Test
    fun convertPhaseToDescription_0point4_waxingGibbous() {
        Assert.assertEquals("Waxing\nGibbous", MoonPhaseConverter.convertPhaseToDescription(0.4))
    }

    @Test
    fun convertPhaseToDescription_0point5_fullMoon() {
        Assert.assertEquals("Full\nMoon", MoonPhaseConverter.convertPhaseToDescription(0.5))
    }

    @Test
    fun convertPhaseToDescription_0point6_waningGibbous() {
        Assert.assertEquals("Waning\nGibbous", MoonPhaseConverter.convertPhaseToDescription(0.6))
    }

    @Test
    fun convertPhaseToDescription_0point75_lastQuarter() {
        Assert.assertEquals("Last\nQuarter", MoonPhaseConverter.convertPhaseToDescription(0.75))
    }

    @Test
    fun convertPhaseToDescription_0point9() {
        Assert.assertEquals("Waning\nCrescent", MoonPhaseConverter.convertPhaseToDescription(0.9))
    }
}