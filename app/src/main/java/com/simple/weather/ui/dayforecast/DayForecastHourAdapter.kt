package com.simple.weather.ui.dayforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.R
import com.simple.weather.data.models.HourlyForecast
import com.simple.weather.databinding.ItemHourForecastBinding

class DayForecastHourAdapter: RecyclerView.Adapter<DayForecastHourViewHolder>() {

    private var hourlyForecasts: List<HourlyForecast> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayForecastHourViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemHourForecastBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_hour_forecast, parent, false)
        return DayForecastHourViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return hourlyForecasts.size
    }

    override fun onBindViewHolder(holder: DayForecastHourViewHolder, position: Int) {
        holder.bindItem(hourlyForecasts[position])
    }

    fun setHourlyForecasts(forecasts: List<HourlyForecast>) {
        hourlyForecasts = forecasts
        notifyDataSetChanged()
    }
}