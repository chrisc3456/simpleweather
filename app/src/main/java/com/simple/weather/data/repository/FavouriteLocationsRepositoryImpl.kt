package com.simple.weather.data.repository

import com.simple.weather.data.db.WeatherDatabase
import com.simple.weather.data.models.FavouriteLocationSummary
import com.simple.weather.data.models.Result
import javax.inject.Inject

class FavouriteLocationsRepositoryImpl @Inject constructor(private val weatherDatabase: WeatherDatabase): FavouriteLocationsRepository {

    override fun getFavouriteLocationSummaries(): Result<List<FavouriteLocationSummary>> {
        val locations = weatherDatabase.locationDao().getAllLocations()
        return Result.completeWithSuccess(locations.map {
            FavouriteLocationSummary(it.id!!, it.name, "clear-day", 17)
        })
    }
}