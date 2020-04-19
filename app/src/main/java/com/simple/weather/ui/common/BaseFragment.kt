package com.simple.weather.ui.common

import android.content.Context
import androidx.fragment.app.Fragment
import com.simple.weather.app.SimpleWeatherApp

open class BaseFragment: Fragment() {
    lateinit var weatherApplication: SimpleWeatherApp

    override fun onAttach(context: Context) {
        super.onAttach(context)
        weatherApplication = requireActivity().application as SimpleWeatherApp
    }
}