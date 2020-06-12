package com.simple.weather.data.repository

import com.simple.weather.data.models.CurrentSnapshot
import com.simple.weather.data.models.LocationSummary
import com.simple.weather.data.models.Result

interface CurrentSnapshotRepository {
    suspend fun getCurrentSnapshot(latitude: Double, longitude: Double): Result<CurrentSnapshot>
    suspend fun getLocationSummary(latitude: Double, longitude: Double): Result<LocationSummary>
    suspend fun saveFavouriteLocation(name: String, latitude: Double, longitude: Double)
    suspend fun getFavouriteLocation(id: Int): LocationSummary
}