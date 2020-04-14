package com.simple.weather.ui.currentsnapshot

import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.data.models.DailySnapshot
import com.simple.weather.databinding.ItemDayForecastBinding
import com.simple.weather.util.WeatherIconConverter

class CurrentSnapshotWeekViewHolder(val binding: ItemDayForecastBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindItem(snapshot: DailySnapshot) {
        binding.dailysnapshot = snapshot
        binding.dailysnapshoticon = getIconForDay(snapshot.iconDescription)
    }

    private fun getIconForDay(iconDescription: String): Int {
        return WeatherIconConverter.getIconIdForDescription(iconDescription)
    }
}