package com.simple.weather.ui.weekforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.simple.weather.R
import com.simple.weather.databinding.FragmentWeekForecastBinding
import com.simple.weather.ui.common.BaseFragment
import com.simple.weather.util.WeatherIconConverter
import kotlinx.android.synthetic.main.fragment_week_forecast.*
import java.text.SimpleDateFormat
import java.util.*

class WeekForecastFragment : BaseFragment() {

    private lateinit var weekForecastBinding: FragmentWeekForecastBinding
    private lateinit var weekForecastAdapter: WeekForecastViewPagerAdapter
    private var defaultForecastTime: Long = 0
    private var defaultTab: Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_week_forecast, container, false)

        defaultForecastTime = WeekForecastFragmentArgs.fromBundle(requireArguments()).forecastTime
        setupBindings(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupViewPager()
        setupTabLayout()
    }

    private fun setupBindings(view: View) {
        val defaultIconDesc = WeekForecastFragmentArgs.fromBundle(requireArguments()).defaultWeatherIcon
        weekForecastBinding = FragmentWeekForecastBinding.bind(view)
        weekForecastBinding.lifecycleOwner = viewLifecycleOwner
        weekForecastBinding.currentDayBackdrop = WeatherIconConverter.getBackdropIdForDescription(defaultIconDesc)
    }

    private fun setupViewPager() {
        weekForecastAdapter = WeekForecastViewPagerAdapter(this)
        viewPagerWeekForecast.adapter = weekForecastAdapter
        viewPagerWeekForecast.offscreenPageLimit = 1

        // Register a callback for page changes so we can pull the updated icon description from the selected day fragment to update the background for the host fragment
        viewPagerWeekForecast.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                weekForecastAdapter.getIconDescForPosition(position)?.let { updateBackdrop(it) }
            }
        })
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
                title = weatherApplication.currentLocationName
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

    private fun updateBackdrop(iconDescription: String) {
        imageViewBackdrop.setImageResource(WeatherIconConverter.getBackdropIdForDescription(iconDescription))
    }
}
