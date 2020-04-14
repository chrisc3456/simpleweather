package com.simple.weather

import com.simple.weather.util.WindBearingConverter
import org.junit.Assert
import org.junit.Test

class WindBearingConverterTest {

    @Test
    fun convertDegreesToCompass_0degrees_north() {
        Assert.assertEquals("N", WindBearingConverter.convertDegreesToCompass(0))
    }

    @Test
    fun convertDegreesToCompass_23degrees_northNorthEast() {
        Assert.assertEquals("NNE", WindBearingConverter.convertDegreesToCompass(23))
    }

    @Test
    fun convertDegreesToCompass_45degrees_northEast() {
        Assert.assertEquals("NE", WindBearingConverter.convertDegreesToCompass(45))
    }

    @Test
    fun convertDegreesToCompass_68degrees_eastNorthEast() {
        Assert.assertEquals("ENE", WindBearingConverter.convertDegreesToCompass(68))
    }

    @Test
    fun convertDegreesToCompass_90degrees_east() {
        Assert.assertEquals("E", WindBearingConverter.convertDegreesToCompass(90))
    }

    @Test
    fun convertDegreesToCompass_112degrees_eastSouthEast() {
        Assert.assertEquals("ESE", WindBearingConverter.convertDegreesToCompass(112))
    }

    @Test
    fun convertDegreesToCompass_134degrees_southEast() {
        Assert.assertEquals("SE", WindBearingConverter.convertDegreesToCompass(134))
    }

    @Test
    fun convertDegreesToCompass_156degrees_southSouthEast() {
        Assert.assertEquals("SSE", WindBearingConverter.convertDegreesToCompass(156))
    }

    @Test
    fun convertDegreesToCompass_180degrees_south() {
        Assert.assertEquals("S", WindBearingConverter.convertDegreesToCompass(180))
    }

    @Test
    fun convertDegreesToCompass_202degrees_southSouthWest() {
        Assert.assertEquals("SSW", WindBearingConverter.convertDegreesToCompass(202))
    }

    @Test
    fun convertDegreesToCompass_224degrees_southWest() {
        Assert.assertEquals("SW", WindBearingConverter.convertDegreesToCompass(224))
    }

    @Test
    fun convertDegreesToCompass_246degrees_westSouthWest() {
        Assert.assertEquals("WSW", WindBearingConverter.convertDegreesToCompass(246))
    }

    @Test
    fun convertDegreesToCompass_268degrees_west() {
        Assert.assertEquals("W", WindBearingConverter.convertDegreesToCompass(268))
    }

    @Test
    fun convertDegreesToCompass_290degrees_westNorthWest() {
        Assert.assertEquals("WNW", WindBearingConverter.convertDegreesToCompass(290))
    }

    @Test
    fun convertDegreesToCompass_312degrees_northWest() {
        Assert.assertEquals("NW", WindBearingConverter.convertDegreesToCompass(312))
    }

    @Test
    fun convertDegreesToCompass_334degrees_northNorthWest() {
        Assert.assertEquals("NNW", WindBearingConverter.convertDegreesToCompass(334))
    }

    @Test
    fun convertDegreesToCompass_350degrees_north() {
        Assert.assertEquals("N", WindBearingConverter.convertDegreesToCompass(350))
    }
}