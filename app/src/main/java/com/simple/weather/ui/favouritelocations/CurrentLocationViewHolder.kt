package com.simple.weather.ui.favouritelocations

import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.R

class CurrentLocationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            it.rootView.findViewById<DrawerLayout>(R.id.drawerLayoutMain).closeDrawer(GravityCompat.START)
            it.findNavController().navigate(R.id.currentSnapshotFragment)
        }
    }
}