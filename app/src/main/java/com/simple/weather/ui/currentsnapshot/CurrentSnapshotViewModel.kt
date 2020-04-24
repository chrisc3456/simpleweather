package com.simple.weather.ui.currentsnapshot

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.libraries.places.api.model.Place
import com.simple.weather.SimpleWeatherApp
import com.simple.weather.data.models.CurrentSnapshot
import com.simple.weather.data.models.LocationSummary
import com.simple.weather.data.models.Result
import com.simple.weather.data.repository.CurrentSnapshotRepository
import com.simple.weather.util.LocationFinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentSnapshotViewModel @Inject constructor(private val application: SimpleWeatherApp, private val repository: CurrentSnapshotRepository) : AndroidViewModel(application) {

    val snapshotResult = MutableLiveData<Result<CurrentSnapshot>>().apply { value = Result.loading(null) }
    val locationResult = MutableLiveData<Result<LocationSummary>>().apply { value = Result.loading(null) }
    val locationPermissionRequired = MutableLiveData<Boolean>()

    /**
     * Request a forecast snapshot from the repository for the specified place, or using location services if none provided
     */
    fun requestSnapshot(place: Place?) {
        if (place == null) {
            checkLocationPermissions()
        } else {
            locationResult.postValue(
                Result.completeWithSuccess(
                    LocationSummary(place.latLng?.latitude ?: 0.0, place.latLng?.longitude ?: 0.0, place.name ?: "")
                )
            )
            getForecastSnapshot(place.latLng?.latitude ?: 0.0, place.latLng?.longitude ?: 0.0)
        }
    }

    /**
     * Check whether the user has already granted location permissions, otherwise update the live data so permission can be requested by the UI
     */
    private fun checkLocationPermissions() {
        if (ContextCompat.checkSelfPermission(application, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            findLocation()
        }
        else {
            locationPermissionRequired.value = true
        }
    }

    /**
     * Obtain the current location to obtain longitude and latitude coordinate parameters for a weather request
     */
    private fun findLocation() {
        LocationFinder.findLocation(application,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    val location = locationResult.locations.first()
                    getForecastSnapshot(location.latitude, location.longitude)
                    getLocationSummary(location.latitude, location.longitude)
                }
            }
        )
    }

    /**
     * Perform repository query to obtain forecast details using a coroutine on the IO thread to avoid locking the main one
     */
    private fun getForecastSnapshot(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {

            // Need to use asynchronous postValue rather than '.value =' to post the value back to the main thread from this background one
            snapshotResult.postValue(
                repository.getCurrentSnapshot(latitude, longitude)
            )
        }
    }

    /**
     * Perform repository query to obtain readable location summary information using a coroutine on the IO thread to avoid locking the main one
     */
    private fun getLocationSummary(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {

            // Need to use asynchronous postValue rather than '.value =' to post the value back to the main thread from this background one
            locationResult.postValue(
                repository.getLocationSummary(latitude, longitude)
            )
        }
    }
}