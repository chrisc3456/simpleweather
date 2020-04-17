package com.simple.weather.ui.dayforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.simple.weather.R
import com.simple.weather.ui.weekforecast.ARG_DAY_NUMBER

class DayForecastFragment : Fragment() {

    private var dayNumber: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_day_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dayNumber = getForecastDayNumber()
    }

    private fun getForecastDayNumber(): Int {
        return arguments?.takeIf {
            it.containsKey(ARG_DAY_NUMBER)
        }?.getInt(ARG_DAY_NUMBER) ?: 0
    }
}
