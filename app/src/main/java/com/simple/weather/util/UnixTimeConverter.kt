package com.simple.weather.util

import java.util.*

object UnixTimeConverter {
    fun getCalendarFromUnixTime(time: Long): Calendar {
        return Calendar.getInstance().apply { timeInMillis = time * 1000 }
    }
}