package com.simple.weather.util

object UvIndexConverter {

    fun getUvIndexAsDisplayText(index: Int): String {
        return when (index) {
            in 0..2 -> "L"
            in 3..5 -> "M"
            in 6..7 -> "H"
            in 7..10 -> "VH"
            else -> "E"
        }
    }
}