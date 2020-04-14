package com.simple.weather.di.module

import androidx.lifecycle.ViewModelProvider
import com.simple.weather.di.annotation.ViewModelKey
import com.simple.weather.ui.common.ViewModelFactory
import com.simple.weather.ui.currentsnapshot.CurrentSnapshotViewModel
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
}