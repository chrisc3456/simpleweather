package com.simple.weather.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DarkSkyWeatherForecastService {

    // To get a forecast for a specific time add a {time} element to the path with the format [YYYY]-[MM]-[DD]T[HH]:[MM]:[SS][timezone]...
    // @Path("time") time: String

    @GET("forecast/{apiKey}/{latitude},{longitude}")
    fun getWeatherForecast(
        @Path("apiKey") apiKey: String,
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        @Query("units") units: String,
        @Query("exclude") exclude: List<String>
    ): Call<DarkSkyForecastResponse.ForecastResponse>
}