package com.simple.weather.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleGeocodingService {

    @GET("json")
    fun getLocationForCoordinates(
        @Query("key") key: String,
        @Query("latlng") latitudeLongitude: String, //[latitude],[longitude]
        @Query("result_type") resultType: String
    ): Call<GoogleGeocodingResponse.GeocodingResponse>
}