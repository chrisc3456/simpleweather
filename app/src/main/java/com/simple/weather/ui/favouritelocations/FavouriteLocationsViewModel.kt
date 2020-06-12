package com.simple.weather.ui.favouritelocations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.data.models.FavouriteLocationSummary
import com.simple.weather.data.models.Result
import com.simple.weather.data.repository.FavouriteLocationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteLocationsViewModel @Inject constructor(private val repository: FavouriteLocationsRepository): ViewModel() {

    val favouriteLocations = MutableLiveData<Result<List<FavouriteLocationSummary>>>().apply { value = Result.loading(null) }

    fun requestFavouriteLocations() {
        getFavouriteLocations()
    }

    /**
     * Perform repository query to obtain location summary details using a coroutine on the IO thread to avoid locking the main one
     */
    private fun getFavouriteLocations() {
        viewModelScope.launch(Dispatchers.IO) {

            // Need to use asynchronous postValue rather than '.value =' to post the value back to the main thread from this background one
            favouriteLocations.postValue(
                repository.getFavouriteLocationSummaries()
            )
        }
    }
}