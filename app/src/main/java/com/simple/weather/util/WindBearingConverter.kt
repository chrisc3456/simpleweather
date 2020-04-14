package com.simple.weather.util

object WindBearingConverter {
    private val compassDirections = listOf("N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW", "N")

    fun convertDegreesToCompass(degrees: Int): String {
        val compassPosition = ((degrees / 22.5) + 0.5).toInt()    // Add 0.5 to avoid clashes at position boundaries
        return compassDirections[compassPosition]
    }
}