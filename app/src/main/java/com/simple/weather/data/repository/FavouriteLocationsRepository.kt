package com.simple.weather.data.repository

import com.simple.weather.data.models.FavouriteLocationSummary
import com.simple.weather.data.models.Result

interface FavouriteLocationsRepository {
    fun getFavouriteLocationSummaries(): Result<List<FavouriteLocationSummary>>
}