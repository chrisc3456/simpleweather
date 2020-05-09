package com.simple.weather.ui.dayforecast

import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.data.models.DayForecast
import com.simple.weather.databinding.ItemDaySummaryBinding
import com.simple.weather.util.WeatherIconConverter

class DayForecastSummaryViewHolder(val binding: ItemDaySummaryBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindItem(forecast: DayForecast) {
        binding.daySummary = forecast
        binding.daySummaryIcon = getIconForDay(forecast.iconDescription)
    }

    private fun getIconForDay(iconDescription: String): Int {
        return WeatherIconConverter.getIconIdForDescription(iconDescription)
    }
}