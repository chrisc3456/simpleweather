package com.simple.weather.data.models

import java.text.SimpleDateFormat
import java.util.*

data class DayForecast(
    val time: Long,
    val sunrise: Long,
    val sunset: Long,
    val iconDescription: String,
    val summary: String,
    val temperatureMax: Int,
    val temperatureMin: Int,
    val hourlyForecasts: List<HourlyForecast>
) {
    fun getSunriseTime(): String {
        return SimpleDateFormat("K:mm a", Locale.getDefault()).format(Date(sunrise * 1000))
    }

    fun getSunsetTime(): String {
        return SimpleDateFormat("K:mm a", Locale.getDefault()).format(Date(sunset * 1000))
    }
}