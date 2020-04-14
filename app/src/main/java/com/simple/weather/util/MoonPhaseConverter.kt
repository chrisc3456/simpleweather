package com.simple.weather.util

object MoonPhaseConverter {

    fun convertPhaseToDescription(phaseFraction: Double): String {
        return when (phaseFraction) {
            0.00 -> "New Moon"
            in 0.01..0.24-> "Waxing Crescent"
            0.25 -> "First Quarter"
            in 0.26..0.49-> "Waxing Gibbous"
            0.50 -> "Full Moon"
            in 0.51..0.74-> "Waning Gibbous"
            0.75 -> "Last Quarter"
            else -> "Waning Crescent"
        }
    }
}