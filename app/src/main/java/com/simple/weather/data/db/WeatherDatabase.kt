package com.simple.weather.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavouriteLocationEntity::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun locationDao(): FavouriteLocationEntityDao
}