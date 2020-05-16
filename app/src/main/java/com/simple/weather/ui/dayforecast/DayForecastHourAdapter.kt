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
    private lateinit var attachedToRecycler: RecyclerView
    var expandedPosition = RecyclerView.NO_POSITION
    var prevExpandedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayForecastHourViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemHourForecastBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_hour_forecast, parent, false)
        return DayForecastHourViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return hourlyForecasts.size
    }

    override fun onBindViewHolder(holder: DayForecastHourViewHolder, position: Int) {
        holder.bindItem(this, hourlyForecasts[position])
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        attachedToRecycler = recyclerView
    }

    fun setHourlyForecasts(forecasts: List<HourlyForecast>) {
        hourlyForecasts = forecasts
        notifyDataSetChanged()
    }
}