package com.simple.weather.di.module

import com.simple.weather.app.SimpleWeatherApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @Module - tells Dagger that the AppModule class will provide app-wide dependencies. Larger applications may have multiple modules
 */
@Module
class AppModule(private val app: SimpleWeatherApp) {

    @Singleton
    @Provides
    fun provideApp() = app
}