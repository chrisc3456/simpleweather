package com.simple.weather.di.module

import com.simple.weather.data.remote.DarkSkyWeatherForecastService
import com.simple.weather.data.remote.GoogleGeocodingService
import com.simple.weather.data.repository.CurrentSnapshotRepository
import com.simple.weather.data.repository.ForecastCurrentRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    /**
     * Singleton - tells Dagger that there should only be one instance of this dependency (can be expensive to create)
     * Provides - tells Dagger that the method will provide a certain type of dependency, in this case a Retrofit service
     *
     * Whenever Dagger needs to provide an instance of type [CurrentSnapshotRepository], this function is executed returning an instance of the concrete implementation
     */

    @Singleton
    @Provides
    fun provideCurrentSnapshotRepository(forecastService: DarkSkyWeatherForecastService, geocodingService: GoogleGeocodingService): CurrentSnapshotRepository {
        return ForecastCurrentRepository(forecastService, geocodingService)
    }
}