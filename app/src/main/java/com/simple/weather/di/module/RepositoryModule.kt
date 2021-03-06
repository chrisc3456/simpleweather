package com.simple.weather.di.module

import com.simple.weather.api.DarkSkyWeatherForecastService
import com.simple.weather.api.GoogleGeocodingService
import com.simple.weather.data.db.WeatherDatabase
import com.simple.weather.data.repository.*
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
    fun provideCurrentSnapshotRepository(forecastService: DarkSkyWeatherForecastService, geocodingService: GoogleGeocodingService, weatherDatabase: WeatherDatabase): CurrentSnapshotRepository {
        return CurrentSnapshotRepositoryImpl(forecastService, geocodingService, weatherDatabase)
    }

    @Singleton
    @Provides
    fun provideDayForecastRepository(forecastService: DarkSkyWeatherForecastService): DayForecastRepository {
        return DayForecastRepositoryImpl(forecastService)
    }

    @Singleton
    @Provides
    fun provideFavouriteLocationsRepository(weatherDatabase: WeatherDatabase): FavouriteLocationsRepository {
        return FavouriteLocationsRepositoryImpl(weatherDatabase)
    }
}