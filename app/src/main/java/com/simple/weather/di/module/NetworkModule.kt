package com.simple.weather.di.module

import com.simple.weather.BuildConfig
import com.simple.weather.api.DarkSkyWeatherForecastService
import com.simple.weather.api.GoogleGeocodingService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    /**
     * Singleton - tells Dagger that there should only be one instance of this dependency (can be expensive to create)
     * Provides - tells Dagger that the method will provide a certain type of dependency, in this case a Retrofit service
     *
     * Whenever Dagger needs to provide an instance of type [DarkSkyWeatherForecastService], this function is executed
     */
    @Singleton
    @Provides
    fun provideDarkSkyDailyForecastService(): DarkSkyWeatherForecastService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.DarkSkyBaseUrl)
            .build()
            .create(DarkSkyWeatherForecastService::class.java)
    }

    @Singleton
    @Provides
    fun provideGoogleGeocodingService(): GoogleGeocodingService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.GeocodingBaseUrl)
            .build()
            .create(GoogleGeocodingService::class.java)
    }
}