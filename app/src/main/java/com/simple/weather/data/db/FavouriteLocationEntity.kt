package com.simple.weather.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteLocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val latitude: Double,
    val longitude: Double
)