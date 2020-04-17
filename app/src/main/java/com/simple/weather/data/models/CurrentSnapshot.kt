package com.simple.weather.data.models

import com.simple.weather.util.MoonPhaseConverter
import java.text.SimpleDateFormat
import java.util.*

data class CurrentSnapshot(
    val latitude: Double,
    val longitude: Double,
    val cloudCover: Int,
    val humidity: Int,
    val icon: String,
    val precipitationVolume: Double,
    val precipitationLikelihood: Int,
    val pressure: Int,
    val summary: String,
    val temperature: Int,
    val time: Long,
    val visibility: Int,
    val windBearing: Int,
    val windSpeed: Double,
    val sunrise: Long,
    val sunset: Long,
    val moonPhase: Double,
    val dailySnapshots: List<DailySnapshot>
) {
    fun getSnapshotTime(): String {
        //TODO: Consider timezone issues - response from service is relative to requested location which may be different to local timezone
        return SimpleDateFormat("EEEE d MMMM, Ka", Locale.UK).format(Date(time * 1000))
    }

    fun getSunriseTime(): String {
        return SimpleDateFormat("K:mm a", Locale.UK).format(Date(sunrise * 1000))
    }

    fun getSunsetTime(): String {
        return SimpleDateFormat("K:mm a", Locale.UK).format(Date(sunset * 1000))
    }

    fun getMoonPhaseDescription(): String {
        return MoonPhaseConverter.convertPhaseToDescription(moonPhase)
    }
}