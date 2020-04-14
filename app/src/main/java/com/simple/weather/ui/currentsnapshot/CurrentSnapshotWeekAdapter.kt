package com.simple.weather.ui.currentsnapshot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.R
import com.simple.weather.data.models.DailySnapshot
import com.simple.weather.databinding.ItemDayForecastBinding

class CurrentSnapshotWeekAdapter: RecyclerView.Adapter<CurrentSnapshotWeekViewHolder>() {

    private var dailySnapshots: List<DailySnapshot> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentSnapshotWeekViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemDayForecastBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_day_forecast, parent, false)
        return CurrentSnapshotWeekViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dailySnapshots.size
    }

    override fun onBindViewHolder(holder: CurrentSnapshotWeekViewHolder, position: Int) {
        holder.bindItem(dailySnapshots[position])
    }

    fun setDailySnapshots(snapshots: List<DailySnapshot>) {
        dailySnapshots = snapshots
        notifyDataSetChanged()
    }
}