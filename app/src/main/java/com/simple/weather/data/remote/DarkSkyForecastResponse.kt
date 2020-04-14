package com.simple.weather.data.remote

import com.simple.weather.data.models.CurrentSnapshot
import com.simple.weather.data.models.DailySnapshot

object DarkSkyForecastResponse {

    /**
     * Data classes to align with the API provided by Dark Sky - https://darksky.net/
     */

    data class ForecastResponse(
        val latitude: Double,
        val longitude: Double,
        val timezone: String,
        val currently: ForecastDataPoint,
        val minutely: ForecastMinutely,
        val hourly: ForecastHourly,
        val daily: ForecastDaily
    )

    data class ForecastMinutely(
        val summary: String,
        val icon: String,
        val data: List<ForecastDataPoint>
    )

    data class ForecastHourly(
        val summary: String,
        val icon: String,
        val data: List<ForecastDataPoint>
    )

    data class ForecastDaily(
        val data: List<ForecastDataPoint>
    )

    data class ForecastDataPoint(
        val sunriseTime: Long,
        val sunsetTime: Long,
        val moonPhase: Double,
        val apparentTemperature: Double,
        val cloudCover: Double,
        val dewPoint: Double,
        val humidity: Double,
        val icon: String,
        val nearestStormBearing: Double,
        val nearestStormDistance: Double,
        val ozone: Double,
        val precipAccumulation: Double,
        val precipIntensity: Double,
        val precipIntensityError: Double,
        val precipIntensityMax: Double,
        val precipIntensityMaxTime: Long,
        val precipProbability: Double,
        val precipType: String,
        val pressure: Double,
        val summary: String,
        val temperature: Double,
        val temperatureHigh: Double,
        val temperatureHighTime: Long,
        val temperatureLow: Double,
        val temperatureLowTime: Long,
        val apparentTemperatureHigh: Double,
        val apparentTemperatureHighTime: Long,
        val apparentTemperatureLow: Double,
        val apparentTemperatureLowTime: Long,
        val time: Long,
        val uvIndex: Int,
        val uvIndexTime: Long,
        val visibility: Double,
        val windBearing: Int,
        val windGust: Double,
        val windGustTime: Long,
        val windSpeed: Double
    )

    /**
     * Converter function to map api response object to internal model
     */
    fun ForecastResponse.toCurrentSnapshot(): CurrentSnapshot {
        val currently = this.currently
        val daily = this.daily.data.first()

        return CurrentSnapshot(
            latitude = latitude,
            longitude = longitude,
            cloudCover = (currently.cloudCover * 100).toInt(),
            humidity = (currently.humidity * 100).toInt(),
            icon = currently.icon,
            precipitationVolume = currently.precipIntensity,
            precipitationLikelihood = (currently.precipProbability * 100).toInt(),
            pressure = currently.pressure.toInt(),
            summary = minutely.summary,
            temperature = currently.temperature.toInt(),
            time = currently.time,
            visibility = (currently.visibility).toInt(),
            windBearing = currently.windBearing,
            windSpeed = currently.windSpeed,
            sunrise = daily.sunriseTime,
            sunset = daily.sunsetTime,
            moonPhase = daily.moonPhase,
            dailySnapshots = this.daily.data.toDailySnapshot()
        )
    }

    /**
     * Converter function to map a list of data points to internal model of daily forecast snapshots
     */
    private fun List<ForecastDataPoint>.toDailySnapshot(): List<DailySnapshot> {
        return map {
            DailySnapshot(
                time = it.time,
                iconDescription = it.icon,
                temperatureMax = it.temperatureHigh.toInt(),
                temperatureMin = it.temperatureLow.toInt()
            )
        }
    }
}