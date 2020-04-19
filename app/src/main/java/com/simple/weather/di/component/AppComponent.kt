package com.simple.weather.di.component

import android.app.Application
import com.simple.weather.di.module.AppModule
import com.simple.weather.di.module.NetworkModule
import com.simple.weather.di.module.RepositoryModule
import com.simple.weather.di.module.ViewModelModule
import com.simple.weather.ui.currentsnapshot.CurrentSnapshotFragment
import com.simple.weather.ui.dayforecast.DayForecastFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


/**
 * @Singleton - tells Dagger that there should only be one instance of this component
 * @Component - used to connect objects to their dependencies
 *
 * The 'modules' attribute tells Dagger which modules to include when building the dependency graph
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class, RepositoryModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }

    // This tells Dagger that the appropriate fragments can request injection so the dependency graph needs to satisfy the field dependencies that the fragment is requesting
    fun inject(currentSnapshotFragment: CurrentSnapshotFragment)
    fun inject(dayForecastFragment: DayForecastFragment)
}