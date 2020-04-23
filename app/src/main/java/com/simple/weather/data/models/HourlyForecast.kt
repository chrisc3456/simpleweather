package com.simple.weather.data.models

import com.simple.weather.util.UvIndexConverter

data class HourlyForecast(
    val hour: Int,
    val minutes: Int,
    val temperature: Int,
    val feelsLike: Int,
    val windSpeed: Int,
    val rainChance: Int,
    val uvLevel: Int,
    val summary: String,
    val iconDescription: String
) {
    fun getDisplayHours(): String {
        return hour.toString().padStart(2, '0')
    }

    fun getDisplayMinutes(): String {
        return minutes.toString().padStart(2, '0')
    }

    fun getDisplayUvIndex(): String {
        return UvIndexConverter.getUvIndexAsDisplayText(uvLevel)
    }
}