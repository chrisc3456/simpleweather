package com.simple.weather.di.module

import androidx.room.Room
import com.simple.weather.SimpleWeatherApp
import com.simple.weather.data.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val app: SimpleWeatherApp) {

    @Singleton
    @Provides
    fun provideDatabase(): WeatherDatabase =
        Room.databaseBuilder(
            app.applicationContext,
            WeatherDatabase::class.java,
            "weather-db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideLocationDao(db: WeatherDatabase) = db.locationDao()
}