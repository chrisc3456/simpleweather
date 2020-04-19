package com.simple.weather.data.repository

import com.simple.weather.BuildConfig
import com.simple.weather.data.models.DayForecast
import com.simple.weather.data.models.Result
import com.simple.weather.data.remote.DarkSkyForecastResponse.toDayForecast
import com.simple.weather.data.remote.DarkSkyWeatherForecastService
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastDayRepository @Inject constructor(private val forecastService: DarkSkyWeatherForecastService): DayForecastRepository {

    //TODO: Implement Room as a local db to support scenarios where network is unavailable - i.e. consider showing previously obtained forecast details

    override suspend fun getDayForecast(latitude: Double, longitude: Double, timeStart: Long): Result<DayForecast> {
        return getDayForecastRemote(latitude, longitude, timeStart)
    }

    /**
     * Process a call to an external service to obtain a weather forecast for the specified location coordinates
     */
    private fun getDayForecastRemote(latitude: Double, longitude: Double, timeStart: Long): Result<DayForecast> {
        val serviceCall = forecastService.getWeatherForecastFromTime(
            BuildConfig.DarkSkyApiKey,
            latitude,
            longitude,
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault()).format(Date(timeStart)),             //[YYYY]-[MM]-[DD]T[HH]:[MM]:[SS][timezone]
            "uk2",      //TODO: Update to use shared preferences to specify required units
            listOf("currently", "minutely", "alerts", "flags")
        )

        return try {
            val response = serviceCall.execute()

            if (response.isSuccessful && response.body() != null) {
                Result.completeWithSuccess(response.body()!!.toDayForecast())
            } else {
                Result.completeWithError("Service error: ${response.raw()}")
            }

        } catch (e: IOException) {
            Result.completeWithError("Service exception: ${e.message}")
        }
    }
}