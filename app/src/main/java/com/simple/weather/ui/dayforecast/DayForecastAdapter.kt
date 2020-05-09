package com.simple.weather.ui.dayforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.R
import com.simple.weather.data.models.DayForecast

class DayForecastAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dayForecast: DayForecast? = null
    private lateinit var attachedToRecycler: RecyclerView
    var expandedPosition = RecyclerView.NO_POSITION
    var prevExpandedPosition = RecyclerView.NO_POSITION

    private enum class DayForecastViewType {
        DaySummary,
        HourForecast
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            DayForecastViewType.DaySummary.ordinal -> DayForecastSummaryViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_day_summary, parent, false))
            else -> DayForecastHourViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_hour_forecast, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return dayForecast?.hourlyForecasts?.size?.plus(1) ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> DayForecastViewType.DaySummary.ordinal
            else -> DayForecastViewType.HourForecast.ordinal
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            DayForecastViewType.DaySummary.ordinal -> (holder as DayForecastSummaryViewHolder).bindItem(dayForecast!!)
            else -> (holder as DayForecastHourViewHolder).bindItem(this, dayForecast!!.hourlyForecasts[position - 1])
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        attachedToRecycler = recyclerView
    }

    fun setDayForecast(forecast: DayForecast) {
        dayForecast = forecast
        notifyDataSetChanged()
    }
}