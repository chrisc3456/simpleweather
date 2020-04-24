package com.simple.weather.data.repository

import com.simple.weather.BuildConfig
import com.simple.weather.api.DarkSkyForecastResponse.toCurrentSnapshot
import com.simple.weather.api.DarkSkyWeatherForecastService
import com.simple.weather.api.GoogleGeocodingResponse.toLocationSummary
import com.simple.weather.api.GoogleGeocodingService
import com.simple.weather.data.models.CurrentSnapshot
import com.simple.weather.data.models.LocationSummary
import com.simple.weather.data.models.Result
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastCurrentRepository @Inject constructor(private val forecastService: DarkSkyWeatherForecastService, private val geocodingService: GoogleGeocodingService): CurrentSnapshotRepository {

    //TODO: Implement Room as a local db to support scenarios where network is unavailable - i.e. consider showing previously obtained forecast details

    override suspend fun getCurrentSnapshot(latitude: Double, longitude: Double): Result<CurrentSnapshot> {
        return getCurrentSnapshotRemote(latitude, longitude)
    }

    override suspend fun getLocationSummary(latitude: Double, longitude: Double): Result<LocationSummary> {
        return getLocationSummaryRemote(latitude, longitude)
    }

    /**
     * Process a call to an external service to obtain a weather forecast for the specified location coordinates
     */
    private fun getCurrentSnapshotRemote(latitude: Double, longitude: Double): Result<CurrentSnapshot> {
        val serviceCall = forecastService.getWeatherForecast(
            BuildConfig.DarkSkyApiKey,
            latitude,
            longitude,
            "uk2",      //TODO: Update to use shared preferences to specify required units
            listOf("hourly", "minutely", "alerts", "flags")
        )

        return try {
            val response = serviceCall.execute()

            if (response.isSuccessful && response.body() != null) {
                Result.completeWithSuccess(response.body()!!.toCurrentSnapshot())
            } else {
                Result.completeWithError("Service error: ${response.raw()}")
            }

        } catch (e: IOException) {
            Result.completeWithError("Service exception: ${e.message}")
        }
    }

    /**
     * Process a call to an external service to obtain reverse geocoded summary details of the specified location coordinates
     */
    private fun getLocationSummaryRemote(latitude: Double, longitude: Double): Result<LocationSummary> {
        val serviceCall = geocodingService.getLocationForCoordinates(
            BuildConfig.GeocodingApiKey,
            "$latitude,$longitude",
            "locality"
        )

        return try {
            val response = serviceCall.execute()

            if (response.isSuccessful && response.body() != null) {
                if (response.body()!!.status == "OK") {
                    Result.completeWithSuccess(response.body()!!.toLocationSummary())
                } else {
                    Result.completeWithError("Service response error: ${response.body()!!.status}")
                }
            } else {
                Result.completeWithError("Service error: ${response.raw()}")
            }

        } catch (e: IOException) {
            Result.completeWithError("Service exception: ${e.message}")
        }
    }
}