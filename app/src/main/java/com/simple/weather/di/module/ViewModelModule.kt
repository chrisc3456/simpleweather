package com.simple.weather.di.module

import androidx.lifecycle.ViewModelProvider
import com.simple.weather.di.annotation.ViewModelKey
import com.simple.weather.ui.common.ViewModelFactory
import com.simple.weather.ui.currentsnapshot.CurrentSnapshotViewModel
import com.simple.weather.ui.dayforecast.DayForecastViewModel
import com.simple.weather.ui.favouritelocations.FavouriteLocationsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    /**
     * Provides a binding for references of ViewModelProvider.Factory to use DailySummaryViewModelFactory when used in the context of DailySummaryViewModel
     */

    @Binds
    @IntoMap
    @ViewModelKey(CurrentSnapshotViewModel::class)
    abstract fun bindCurrentSnapshotViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DayForecastViewModel::class)
    abstract fun bindDayForecastViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteLocationsViewModel::class)
    abstract fun bindFavouriteLocationsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}