package com.simple.weather.app

import android.app.Application
import com.simple.weather.di.component.AppComponent
import com.simple.weather.di.component.DaggerAppComponent
import com.simple.weather.di.module.AppModule

/**
 * Application subclass to set up and manage app level state/dependencies
 * Registered in the manifest using android:name=".SimpleWeatherApp"
 */
class SimpleWeatherApp: Application() {

    // Reference to the application dependency graph which is used across the whole app
    val weatherComponent: AppComponent = DaggerAppComponent.builder()
        .application(this)
        .appModule(AppModule(this))
        .build()
}