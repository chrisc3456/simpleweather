package com.simple.weather.ui.favouritelocations

import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.R

class SettingsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            it.rootView.findViewById<DrawerLayout>(R.id.drawerLayoutMain).closeDrawer(GravityCompat.START)
            //TODO: Set on click listener for settings
        }
    }
}