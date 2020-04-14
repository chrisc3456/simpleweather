package com.simple.weather.util

import android.content.Context
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

/**
 * Obtain the current location to obtain longitude and latitude coordinate parameters for a weather request
 * Could use FusedLocationProviderClient.lastLocation from LocationServices however this will be null if no previous location request on the device
 */
object LocationFinder {
    fun findLocation(context: Context, callback: LocationCallback) {
        val locationRequest = getRequest()
        LocationServices.getFusedLocationProviderClient(context).requestLocationUpdates(locationRequest, callback, null)
    }

    private fun getRequest(): LocationRequest {
        return LocationRequest.create().apply {
            interval = 10
            fastestInterval = 10
            numUpdates = 1
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }
}