package com.simple.weather.ui.weekforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.simple.weather.R
import com.simple.weather.app.SimpleWeatherApp
import kotlinx.android.synthetic.main.fragment_week_forecast.*
import java.text.SimpleDateFormat
import java.util.*

class WeekForecastFragment : Fragment() {

    private lateinit var weekForecastAdapter: WeekForecastViewPagerAdapter
    private var defaultForecastTime: Long = 0
    private var defaultTab: Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        defaultForecastTime = WeekForecastFragmentArgs.fromBundle(requireArguments()).forecastTime
        return inflater.inflate(R.layout.fragment_week_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        weekForecastAdapter = WeekForecastViewPagerAdapter(this)
        viewPagerWeekForecast.adapter = weekForecastAdapter
        viewPagerWeekForecast.offscreenPageLimit = 1
    }

    private fun setupTabLayout() {
        TabLayoutMediator(tabLayoutWeekForecast, viewPagerWeekForecast) { tab, position ->
            setupTabForPosition(tab, position)
        }.attach()

        // Setting currentItem doesn't always work with event timing, hence processing in a delayed handler
        // See bottom answer here: https://stackoverflow.com/questions/28968512/viewpager-set-current-page-programmatically
        viewPagerWeekForecast.postDelayed(
            Runnable {
                kotlin.run { viewPagerWeekForecast.setCurrentItem(defaultTab, true) }
            }, 10
        )
    }

    private fun setupTabForPosition(tab: TabLayout.Tab, position: Int) {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.add(Calendar.DAY_OF_YEAR, position)

        // The first tab whose date is beyond that which is provided via arguments, is the one which was selected
        if (defaultTab == -1 && (calendar.timeInMillis / 1000) > defaultForecastTime) {
            defaultTab = position
        }

        tab.text = SimpleDateFormat("EEE", Locale.getDefault()).format(calendar.time)
    }

    private fun setupToolbar() {
        setHasOptionsMenu(true)

        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(toolbarWeekForecast)
            setupActionBarWithNavController(findNavController())

            supportActionBar?.apply {
                title = (requireActivity().application as SimpleWeatherApp).currentLocationName
                setDisplayShowTitleEnabled(true)
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
