package com.simple.weather.ui.favouritelocations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.R
import com.simple.weather.data.models.FavouriteLocationSummary

class FavouriteLocationsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var favourites: List<FavouriteLocationSummary> = listOf()

    private enum class FavouriteLocationsItemType {
        CurrentLocation,
        FavouriteLocation,
        Settings
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            FavouriteLocationsItemType.CurrentLocation.ordinal -> CurrentLocationViewHolder(layoutInflater.inflate(R.layout.item_favourite_current_location, parent, false))
            FavouriteLocationsItemType.Settings.ordinal -> SettingsViewHolder(layoutInflater.inflate(R.layout.item_favourite_settings, parent, false))
            else -> FavouriteLocationsViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_favourite_location, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return favourites.size + 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> FavouriteLocationsItemType.CurrentLocation
            favourites.size + 1 -> FavouriteLocationsItemType.Settings
            else -> FavouriteLocationsItemType.FavouriteLocation
        }.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == FavouriteLocationsItemType.FavouriteLocation.ordinal) {
            (holder as FavouriteLocationsViewHolder).bindItem(favourites[position - 1])
        }
    }

    fun setFavourites(favouritesSummaries: List<FavouriteLocationSummary>) {
        favourites = favouritesSummaries
        notifyDataSetChanged()
    }
}