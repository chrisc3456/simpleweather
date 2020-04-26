package com.simple.weather.util

object MoonPhaseConverter {

    fun convertPhaseToDescription(phaseFraction: Double): String {
        return when (phaseFraction) {
            0.00 -> "New\nMoon"
            in 0.01..0.24-> "Waxing\nCrescent"
            0.25 -> "First\nQuarter"
            in 0.26..0.49-> "Waxing\nGibbous"
            0.50 -> "Full\nMoon"
            in 0.51..0.74-> "Waning\nGibbous"
            0.75 -> "Last\nQuarter"
            else -> "Waning\nCrescent"
        }
    }
}