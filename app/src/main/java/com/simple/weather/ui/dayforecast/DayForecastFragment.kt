package com.simple.weather.ui.dayforecast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.simple.weather.R
import com.simple.weather.data.models.DayForecast
import com.simple.weather.data.models.Result
import com.simple.weather.ui.common.BaseFragment
import com.simple.weather.ui.common.DividerItemDecorator
import com.simple.weather.ui.weekforecast.ARG_DAY_NUMBER
import kotlinx.android.synthetic.main.fragment_day_forecast.*
import java.util.*
import javax.inject.Inject

class DayForecastFragment(val updateDayIcon: (String) -> Unit) : BaseFragment() {

    private val hourForecastAdapter = DayForecastHourAdapter()
    private var dayNumber: Int = 0

    // Inject an instance of the view model from the dagger dependency graph
    @Inject
    lateinit var dayForecastViewModel: DayForecastViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        weatherApplication.weatherComponent.inject(this@DayForecastFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_day_forecast, container, false)

        dayNumber = getForecastDayNumber()
        setupViewModel()
        addObservers()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    private fun getForecastDayNumber(): Int {
        return arguments?.takeIf {
            it.containsKey(ARG_DAY_NUMBER)
        }?.getInt(ARG_DAY_NUMBER) ?: 0
    }

    private fun getForecastTimeStart(): Long {
        val time = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        time.add(Calendar.DAY_OF_YEAR, dayNumber)

        return time.timeInMillis
    }

    private fun setupRecycler() {
        recyclerViewHourForecast.addItemDecoration(
            DividerItemDecorator(ContextCompat.getDrawable(requireContext(), R.drawable.divider_horizontal)!!, DividerItemDecorator.ViewOrientation.VERTICAL)
        )

        recyclerViewHourForecast.adapter = hourForecastAdapter
        recyclerViewHourForecast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupViewModel() {
        dayForecastViewModel.requestForecast(
            weatherApplication.currentLocationLatitude,
            weatherApplication.currentLocationLongitude,
            getForecastTimeStart()
        )
    }

    private fun addObservers() {
        dayForecastViewModel.forecastResult.observe(viewLifecycleOwner, Observer { result ->
            displayForecastResult(result)
        })
    }

    /**
     * Update data binding fields with weather forecast information from the viewmodel
     */
    private fun displayForecastResult(result: Result<DayForecast>) {
        val dayForecast = result.resultData

        if (dayForecast != null) {
            hourForecastAdapter.setHourlyForecasts(dayForecast.hourlyForecasts.take(24))
            updateDayIcon(dayForecast.iconDescription)
        }

        displayResultError(result)
    }
}
