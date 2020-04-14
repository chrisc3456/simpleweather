package com.simple.weather.data.models

import java.text.SimpleDateFormat
import java.util.*

data class DailySnapshot(
    val time: Long,
    val iconDescription: String,
    val temperatureMax: Int,
    val temperatureMin: Int
) {
    fun getTimeAsDayName(): String {
        return SimpleDateFormat("EEE", Locale.getDefault()).format(Date(time * 1000))
    }
}