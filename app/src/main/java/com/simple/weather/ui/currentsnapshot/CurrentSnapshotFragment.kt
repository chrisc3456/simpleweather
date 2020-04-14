package com.simple.weather.ui.currentsnapshot

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.R
import com.simple.weather.app.SimpleWeatherApp
import com.simple.weather.data.models.CurrentSnapshot
import com.simple.weather.data.models.LocationSummary
import com.simple.weather.data.models.Result
import com.simple.weather.databinding.FragmentCurrentSnapshotBinding
import com.simple.weather.util.FadeLayoutAppBarOffsetChangedListener
import com.simple.weather.util.TitleOnCollapseAppbarOffsetListener
import com.simple.weather.util.ToolbarTitleDisplayProvider
import com.simple.weather.util.WeatherIconConverter
import kotlinx.android.synthetic.main.fragment_current_snapshot.*
import kotlinx.android.synthetic.main.layout_week_forecast.*
import javax.inject.Inject

const val PERMISSION_ID_LOCATION = 1

class CurrentSnapshotFragment : Fragment() {

    private lateinit var currentSnapshotDetailsBinding: FragmentCurrentSnapshotBinding
    private val weekForecastAdapter = CurrentSnapshotWeekAdapter()
    private var currentLocationName: String = "Locating..."

    // Inject an instance of the view model from the dagger dependency graph
    @Inject
    lateinit var currentSnapshotViewModel: CurrentSnapshotViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as SimpleWeatherApp).weatherComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_current_snapshot, container, false)

        setupViewModel()
        addObservers()
        setupBindings(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAppbar()
        setupRecycler()
    }

    private fun setupAppbar() {
        collapsingToolbarForecast.setCollapsedTitleTextColor(resources.getColor(R.color.colorPrimary, requireContext().theme))

        // Add offset change listeners to control title display and fading of views depending on scroll position of the appbar layout
        appbar.addOnOffsetChangedListener(TitleOnCollapseAppbarOffsetListener(collapsingToolbarForecast, object: ToolbarTitleDisplayProvider {
            override fun getTitle(): String {
                return currentLocationName
            }
        })
        )
        appbar.addOnOffsetChangedListener(FadeLayoutAppBarOffsetChangedListener(FadeLayoutAppBarOffsetChangedListener.FadeMode.FADE_IN, frameOverlay, 1f))
        appbar.addOnOffsetChangedListener(FadeLayoutAppBarOffsetChangedListener(FadeLayoutAppBarOffsetChangedListener.FadeMode.FADE_OUT, constraintSummaryDetails, 1.5f))
    }

    private fun setupViewModel() {
        currentSnapshotViewModel.requestSnapshot()
    }

    private fun setupRecycler() {
        val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider_vertical)!!)
        recyclerViewDailySnapshot.addItemDecoration(itemDecorator)

        recyclerViewDailySnapshot.adapter = weekForecastAdapter
        recyclerViewDailySnapshot.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }

    private fun setupBindings(view: View) {
        currentSnapshotDetailsBinding = FragmentCurrentSnapshotBinding.bind(view)
        currentSnapshotDetailsBinding.lifecycleOwner = viewLifecycleOwner
        currentSnapshotDetailsBinding.location = "Finding location..."
    }

    private fun addObservers() {
        currentSnapshotViewModel.locationPermissionRequired.observe(viewLifecycleOwner, Observer { result ->
            requestLocationPermissions(result)
        })
        currentSnapshotViewModel.snapshotResult.observe(viewLifecycleOwner, Observer { result ->
            displayForecastResult(result)
        })
        currentSnapshotViewModel.locationResult.observe(viewLifecycleOwner, Observer { result ->
            displayLocationSummary(result)
        })
    }

    /**
     * Request required location permissions
     */
    private fun requestLocationPermissions(isRequired: Boolean) {
        if (isRequired) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_ID_LOCATION
            )
        }
    }

    /**
     * Make the location and weather snapshot request if permissions are granted
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                currentSnapshotViewModel.requestSnapshot()
            }
        }
    }

    /**
     * Update data binding fields with weather forecast information from the viewmodel
     */
    private fun displayForecastResult(result: Result<CurrentSnapshot>) {
        val snapshot = result.resultData

        if (snapshot != null) {
            currentSnapshotDetailsBinding.currentSnapshot = snapshot
            currentSnapshotDetailsBinding.currentSnapshotBackdrop = WeatherIconConverter.getBackdropIdForDescription(snapshot.icon)
            weekForecastAdapter.setDailySnapshots(snapshot.dailySnapshots)
        }

        displayResultError(result)
    }

    /**
     * Update data binding fields with readable location summary information from the viewmodel
     */
    private fun displayLocationSummary(result: Result<LocationSummary>) {
        val locationData = result.resultData

        if (locationData != null) {
            currentLocationName = locationData.name
            currentSnapshotDetailsBinding.location = locationData.name
        }

        displayResultError(result)
    }

    /**
     * Display details of any errors to the user
     */
    private fun displayResultError(result: Result<*>) {
        if (result.state == Result.State.COMPLETE_ERROR) {
            val toast = Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG)
            toast.show()
        }
    }
}
