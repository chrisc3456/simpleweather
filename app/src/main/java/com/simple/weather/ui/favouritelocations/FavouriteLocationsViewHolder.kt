package com.simple.weather.ui.favouritelocations

import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.R
import com.simple.weather.data.models.FavouriteLocationSummary
import com.simple.weather.databinding.ItemFavouriteLocationBinding
import com.simple.weather.ui.currentsnapshot.ARG_LOCATION_ID
import com.simple.weather.util.WeatherIconConverter

class FavouriteLocationsViewHolder(val binding: ItemFavouriteLocationBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindItem(summary: FavouriteLocationSummary) {
        binding.location = summary
        binding.forecastIcon = getIconForDay(summary.iconDescription)

        binding.root.setOnClickListener {
            it.rootView.findViewById<DrawerLayout>(R.id.drawerLayoutMain).closeDrawer(GravityCompat.START)

            val bundle = bundleOf(ARG_LOCATION_ID to summary.locationId)
            it.findNavController().navigate(R.id.currentSnapshotFragment, bundle)
        }
    }

    private fun getIconForDay(iconDescription: String): Int {
        return WeatherIconConverter.getIconIdForDescription(iconDescription)
    }
}