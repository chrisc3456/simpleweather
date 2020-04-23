package com.simple.weather.ui.dayforecast

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.data.models.HourlyForecast
import com.simple.weather.databinding.ItemHourForecastBinding
import com.simple.weather.util.WeatherIconConverter
import kotlinx.android.synthetic.main.item_hour_forecast.view.*

class DayForecastHourViewHolder(val binding: ItemHourForecastBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindItem(adapter: DayForecastHourAdapter, forecast: HourlyForecast) {
        binding.hourlyForecast = forecast
        binding.hourlyForecastIcon = getIconForHour(forecast.iconDescription)

        updateExpandedView(adapter)
    }

    private fun updateExpandedView(adapter: DayForecastHourAdapter) {
        val isExpanded = adapterPosition == adapter.expandedPosition
        binding.root.constraintAdditionalContent.visibility = if (isExpanded) View.VISIBLE else View.GONE
        binding.root.isActivated = isExpanded
        binding.root.setOnClickListener {
            adapter.expandedPosition = if (isExpanded) -1 else adapterPosition

            if (adapter.prevExpandedPosition != RecyclerView.NO_POSITION) {
                adapter.notifyItemChanged(adapter.prevExpandedPosition)
            }

            adapter.notifyItemChanged(adapterPosition)
            adapter.prevExpandedPosition = adapterPosition
        }
    }

    private fun getIconForHour(iconDescription: String): Int {
        return WeatherIconConverter.getIconIdForDescription(iconDescription)
    }
}