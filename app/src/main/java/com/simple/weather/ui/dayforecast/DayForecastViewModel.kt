package com.simple.weather.ui.dayforecast

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.simple.weather.SimpleWeatherApp
import com.simple.weather.data.models.DayForecast
import com.simple.weather.data.models.Result
import com.simple.weather.data.repository.DayForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DayForecastViewModel @Inject constructor(application: SimpleWeatherApp, private val repository: DayForecastRepository) : AndroidViewModel(application) {

    val forecastResult = MutableLiveData<Result<DayForecast>>().apply { value = Result.loading(null) }

    fun requestForecast(latitude: Double, longitude: Double, timeStart: Long) {
        getForecast(latitude, longitude, timeStart)
    }

    /**
     * Perform repository query to obtain forecast details using a coroutine on the IO thread to avoid locking the main one
     */
    private fun getForecast(latitude: Double, longitude: Double, timeStart: Long) {
        viewModelScope.launch(Dispatchers.IO) {

            // Need to use asynchronous postValue rather than '.value =' to post the value back to the main thread from this background one
            forecastResult.postValue(
                repository.getDayForecast(latitude, longitude, timeStart)
            )
        }
    }
}