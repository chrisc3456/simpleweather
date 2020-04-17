package com.simple.weather.ui.weekforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.simple.weather.ui.dayforecast.DayForecastFragment

const val ARG_DAY_NUMBER = "daynumber"

class WeekForecastViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 7
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = DayForecastFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_DAY_NUMBER, position)
        }
        return fragment
    }
}