package com.simple.weather.data.db

import androidx.room.*

@Dao
interface FavouriteLocationEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(location: FavouriteLocationEntity)

    @Delete
    fun deleteLocation(location: FavouriteLocationEntity)

    @Query("SELECT * FROM FavouriteLocationEntity")
    fun getAllLocations(): List<FavouriteLocationEntity>

    @Query("SELECT * FROM FavouriteLocationEntity WHERE id = :id")
    fun getLocation(id: Int): FavouriteLocationEntity

    @Query("SELECT * FROM FavouriteLocationEntity WHERE latitude = :latitude AND longitude = :longitude")
    fun getLocationFromCoords(latitude: Double, longitude: Double): List<FavouriteLocationEntity>
}