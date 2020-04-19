package com.simple.weather.data.models

data class HourlyForecast(
    val hour: Int,
    val minutes: Int,
    val temperature: Int,
    val summary: String,
    val iconDescription: String
) {
    fun getDisplayHours(): String {
        return hour.toString().padStart(2, '0')
    }

    fun getDisplayMinutes(): String {
        return minutes.toString().padStart(2, '0')
    }
}