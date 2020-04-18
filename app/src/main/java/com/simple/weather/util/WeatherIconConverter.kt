package com.simple.weather.util

import com.simple.weather.R

object WeatherIconConverter {

    /**
     * Converts the specified description enum to a corresponding resource id for icon display
     */
    fun getIconIdForDescription(description: String): Int {
        return when (description) {
            "clear-day" -> R.drawable.ic_weather_sun
            "clear-night" -> R.drawable.ic_weather_night_clear
            "rain" -> R.drawable.ic_weather_rain_medium
            "snow" -> R.drawable.ic_weather_snow
            "sleet" -> R.drawable.ic_weather_sleet
            "wind" -> R.drawable.ic_weather_wind
            "fog" -> R.drawable.ic_weather_fog
            "cloudy" -> R.drawable.ic_weather_cloudy
            "partly-cloudy-day" -> R.drawable.ic_weather_partly_cloudy
            "partly-cloudy-night" -> R.drawable.ic_weather_night_partly_cloudy
            "hail" -> R.drawable.ic_weather_hail
            "thunderstorm" -> R.drawable.ic_weather_thunderstorm
            "tornado" -> R.drawable.ic_weather_tornado
            else -> R.drawable.ic_weather_sun
        }
    }

    /**
     * Converts the specified description enum to a corresponding resource id for icon display
     */
    fun getBackdropIdForDescription(description: String): Int {
        return when (description) {
            "clear-day" -> R.drawable.backdrop_gradient_sunny
            "clear-night" -> R.drawable.backdrop_gradient_clear_night
            "rain" -> R.drawable.backdrop_gradient_rainy
            "snow" -> R.drawable.backdrop_gradient_snow
            "sleet" -> R.drawable.backdrop_gradient_sleet
            "wind" -> R.drawable.backdrop_gradient_wind
            "fog" -> R.drawable.backdrop_gradient_fog
            "cloudy" -> R.drawable.backdrop_gradient_cloudy
            "partly-cloudy-day" -> R.drawable.backdrop_gradient_cloudy
            "partly-cloudy-night" -> R.drawable.backdrop_gradient_cloudy_night
            "hail" -> R.drawable.backdrop_gradient_rainy
            "thunderstorm" -> R.drawable.backdrop_gradient_thunderstorm
            "tornado" -> R.drawable.backdrop_gradient_rainy
            else -> R.drawable.backdrop_gradient_sunny
        }
    }
}