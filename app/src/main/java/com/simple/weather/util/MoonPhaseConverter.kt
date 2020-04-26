package com.simple.weather.util

import com.simple.weather.R

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

    fun convertPhaseToIconId(phaseFraction: Double): Int {
        return when (phaseFraction) {
            0.00 -> R.drawable.ic_moon_new
            in 0.01..0.24-> R.drawable.ic_moon_crescent_waxing
            0.25 -> R.drawable.ic_moon_first_quarter
            in 0.26..0.49-> R.drawable.ic_moon_gibbous_waxing
            0.50 -> R.drawable.ic_moon_full
            in 0.51..0.74-> R.drawable.ic_moon_gibbous_waning
            0.75 -> R.drawable.ic_moon_last_quarter
            else -> R.drawable.ic_moon_crescent_waning
        }
    }
}