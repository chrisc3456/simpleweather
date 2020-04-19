package com.simple.weather.data.repository

import com.simple.weather.data.models.DayForecast
import com.simple.weather.data.models.Result

interface DayForecastRepository {
    suspend fun getDayForecast(latitude: Double, longitude: Double, timeStart: Long): Result<DayForecast>
}