package com.simple.weather

import com.simple.weather.util.WeatherIconConverter
import org.junit.Assert
import org.junit.Test

class WeatherIconConverterTest {

    /**
     * getIconIdForDescription tests
     */

    @Test
    fun getIconIdForDescription_clearDay_sun() {
        Assert.assertEquals(R.drawable.ic_weather_sun, WeatherIconConverter.getIconIdForDescription("clear-day"))
    }

    @Test
    fun getIconIdForDescription_clearNight_nightClear() {
        Assert.assertEquals(R.drawable.ic_weather_night_clear, WeatherIconConverter.getIconIdForDescription("clear-night"))
    }

    @Test
    fun getIconIdForDescription_rain_rainMedium() {
        Assert.assertEquals(R.drawable.ic_weather_rain_medium, WeatherIconConverter.getIconIdForDescription("rain"))
    }

    @Test
    fun getIconIdForDescription_snow_snow() {
        Assert.assertEquals(R.drawable.ic_weather_snow, WeatherIconConverter.getIconIdForDescription("snow"))
    }

    @Test
    fun getIconIdForDescription_sleet_sleet() {
        Assert.assertEquals(R.drawable.ic_weather_sleet, WeatherIconConverter.getIconIdForDescription("sleet"))
    }

    @Test
    fun getIconIdForDescription_wind_wind() {
        Assert.assertEquals(R.drawable.ic_weather_wind, WeatherIconConverter.getIconIdForDescription("wind"))
    }

    @Test
    fun getIconIdForDescription_fog_fog() {
        Assert.assertEquals(R.drawable.ic_weather_fog, WeatherIconConverter.getIconIdForDescription("fog"))
    }

    @Test
    fun getIconIdForDescription_cloudy_cloudy() {
        Assert.assertEquals(R.drawable.ic_weather_cloudy, WeatherIconConverter.getIconIdForDescription("cloudy"))
    }

    @Test
    fun getIconIdForDescription_partlyCloudyDay_partlyCloudy() {
        Assert.assertEquals(R.drawable.ic_weather_partly_cloudy, WeatherIconConverter.getIconIdForDescription("partly-cloudy-day"))
    }

    @Test
    fun getIconIdForDescription_partlyCloudyNight_nightPartlyCloudy() {
        Assert.assertEquals(R.drawable.ic_weather_night_partly_cloudy, WeatherIconConverter.getIconIdForDescription("partly-cloudy-night"))
    }

    @Test
    fun getIconIdForDescription_hail_hail() {
        Assert.assertEquals(R.drawable.ic_weather_hail, WeatherIconConverter.getIconIdForDescription("hail"))
    }

    @Test
    fun getIconIdForDescription_thunderstorm_thunderstorm() {
        Assert.assertEquals(R.drawable.ic_weather_thunderstorm, WeatherIconConverter.getIconIdForDescription("thunderstorm"))
    }

    @Test
    fun getIconIdForDescription_tornado_tornado() {
        Assert.assertEquals(R.drawable.ic_weather_tornado, WeatherIconConverter.getIconIdForDescription("tornado"))
    }

    @Test
    fun getIconIdForDescription_other_sun() {
        Assert.assertEquals(R.drawable.ic_weather_sun, WeatherIconConverter.getIconIdForDescription(""))
    }



    /**
     * getBackdropIdForDescription tests
     */

    @Test
    fun getBackdropIdForDescription_clearDay_sun() {
        Assert.assertEquals(R.drawable.backdrop_gradient_sunny, WeatherIconConverter.getBackdropIdForDescription("clear-day"))
    }

    @Test
    fun getBackdropIdForDescription_clearNight_nightClear() {
        Assert.assertEquals(R.drawable.backdrop_gradient_clear_night, WeatherIconConverter.getBackdropIdForDescription("clear-night"))
    }

    @Test
    fun getBackdropIdForDescription_rain_rainMedium() {
        Assert.assertEquals(R.drawable.backdrop_gradient_rainy, WeatherIconConverter.getBackdropIdForDescription("rain"))
    }

    @Test
    fun getBackdropIdForDescription_snow_snow() {
        Assert.assertEquals(R.drawable.backdrop_gradient_rainy, WeatherIconConverter.getBackdropIdForDescription("snow"))
    }

    @Test
    fun getBackdropIdForDescription_sleet_sleet() {
        Assert.assertEquals(R.drawable.backdrop_gradient_rainy, WeatherIconConverter.getBackdropIdForDescription("sleet"))
    }

    @Test
    fun getBackdropIdForDescription_wind_wind() {
        Assert.assertEquals(R.drawable.backdrop_gradient_cloudy, WeatherIconConverter.getBackdropIdForDescription("wind"))
    }

    @Test
    fun getBackdropIdForDescription_fog_fog() {
        Assert.assertEquals(R.drawable.backdrop_gradient_cloudy, WeatherIconConverter.getBackdropIdForDescription("fog"))
    }

    @Test
    fun getBackdropIdForDescription_cloudy_cloudy() {
        Assert.assertEquals(R.drawable.backdrop_gradient_cloudy, WeatherIconConverter.getBackdropIdForDescription("cloudy"))
    }

    @Test
    fun getBackdropIdForDescription_partlyCloudyDay_partlyCloudy() {
        Assert.assertEquals(R.drawable.backdrop_gradient_cloudy, WeatherIconConverter.getBackdropIdForDescription("partly-cloudy-day"))
    }

    @Test
    fun getBackdropIdForDescription_partlyCloudyNight_nightPartlyCloudy() {
        Assert.assertEquals(R.drawable.backdrop_gradient_cloudy_night, WeatherIconConverter.getBackdropIdForDescription("partly-cloudy-night"))
    }

    @Test
    fun getBackdropIdForDescription_hail_hail() {
        Assert.assertEquals(R.drawable.backdrop_gradient_rainy, WeatherIconConverter.getBackdropIdForDescription("hail"))
    }

    @Test
    fun getBackdropIdForDescription_thunderstorm_thunderstorm() {
        Assert.assertEquals(R.drawable.backdrop_gradient_rainy, WeatherIconConverter.getBackdropIdForDescription("thunderstorm"))
    }

    @Test
    fun getBackdropIdForDescription_tornado_tornado() {
        Assert.assertEquals(R.drawable.backdrop_gradient_rainy, WeatherIconConverter.getBackdropIdForDescription("tornado"))
    }

    @Test
    fun getBackdropIdForDescription_other_sun() {
        Assert.assertEquals(R.drawable.backdrop_gradient_sunny, WeatherIconConverter.getBackdropIdForDescription(""))
    }
}