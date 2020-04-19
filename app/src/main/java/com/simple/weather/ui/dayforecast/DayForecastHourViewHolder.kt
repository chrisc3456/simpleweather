package com.simple.weather.ui.dayforecast

import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.data.models.HourlyForecast
import com.simple.weather.databinding.ItemHourForecastBinding
import com.simple.weather.util.WeatherIconConverter

class DayForecastHourViewHolder(val binding: ItemHourForecastBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindItem(forecast: HourlyForecast) {
        binding.hourlyForecast = forecast
        binding.hourlyForecastIcon = getIconForHour(forecast.iconDescription)
    }

    private fun getIconForHour(iconDescription: String): Int {
        return WeatherIconConverter.getIconIdForDescription(iconDescription)
    }
}