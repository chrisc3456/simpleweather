package com.simple.weather.ui.weekforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.simple.weather.ui.dayforecast.DayForecastFragment

const val ARG_DAY_NUMBER = "daynumber"

class WeekForecastViewPagerAdapter(parent: Fragment): FragmentStateAdapter(parent) {

    private val iconDescriptionsForFragments: Array<String?> = arrayOfNulls(itemCount)

    override fun getItemCount(): Int {
        return 7
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = DayForecastFragment { s -> iconDescriptionsForFragments[position] = s }
        fragment.arguments = Bundle().apply {
            putInt(ARG_DAY_NUMBER, position)
        }
        return fragment
    }

    fun getIconDescForPosition(position: Int): String? {
        return iconDescriptionsForFragments[position]
    }
}