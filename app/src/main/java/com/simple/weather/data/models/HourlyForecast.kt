package com.simple.weather.data.models

data class HourlyForecast(
    val hour: String,
    val minutes: String,
    val temperature: String,
    val windSpeed: String,
    val precipitation: String
)