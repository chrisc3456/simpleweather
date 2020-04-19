package com.simple.weather.data.models

data class DayForecast(
    val time: Long,
    val iconDescription: String,
    val hourlyForecasts: List<HourlyForecast>
)