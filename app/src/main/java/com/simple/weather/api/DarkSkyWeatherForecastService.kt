package com.simple.weather.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DarkSkyWeatherForecastService {

    @GET("forecast/{apiKey}/{latitude},{longitude}")
    fun getWeatherForecast(
        @Path("apiKey") apiKey: String,
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        @Query("units") units: String,
        @Query("exclude") exclude: List<String>
    ): Call<DarkSkyForecastResponse.ForecastResponse>

    @GET("forecast/{apiKey}/{latitude},{longitude},{time}")
    fun getWeatherForecastFromTime(
        @Path("apiKey") apiKey: String,
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        @Path("time") time: String,             //[YYYY]-[MM]-[DD]T[HH]:[MM]:[SS][timezone]
        @Query("units") units: String,
        @Query("exclude") exclude: List<String>
    ): Call<DarkSkyForecastResponse.ForecastResponse>
}