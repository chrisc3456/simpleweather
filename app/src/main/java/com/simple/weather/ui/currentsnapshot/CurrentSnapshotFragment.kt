package com.simple.weather.ui.currentsnapshot

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity.RESULT_ERROR
import com.simple.weather.R
import com.simple.weather.data.models.CurrentSnapshot
import com.simple.weather.data.models.LocationSummary
import com.simple.weather.data.models.Result
import com.simple.weather.databinding.FragmentCurrentSnapshotBinding
import com.simple.weather.ui.common.BaseFragment
import com.simple.weather.ui.common.DividerItemDecorator
import com.simple.weather.util.*
import kotlinx.android.synthetic.main.fragment_current_snapshot.*
import kotlinx.android.synthetic.main.layout_week_forecast.*
import javax.inject.Inject

const val PERMISSION_ID_LOCATION = 1
const val REQUEST_CODE_AUTOCOMPLETE = 1
const val ARG_LOCATION_ID = "locationId"

class CurrentSnapshotFragment : BaseFragment() {

    private lateinit var currentSnapshotDetailsBinding: FragmentCurrentSnapshotBinding
    private val weekForecastAdapter = CurrentSnapshotWeekAdapter()
    private var currentPlace: Place? = null
    private var favouriteLocationId: Int? = null

    // Inject an instance of the view model from the dagger dependency graph
    @Inject
    lateinit var currentSnapshotViewModel: CurrentSnapshotViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        weatherApplication.weatherComponent.inject(this@CurrentSnapshotFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PlacesHelper.initialisePlaces(requireContext())
        favouriteLocationId = arguments?.getInt(ARG_LOCATION_ID)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.title = ""
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
        applyTopSystemInsetsToView(appbar)
        setupAppbar()
        setupRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> toggleDrawerState()
            R.id.menu_item_search -> openLocationSearch()
            R.id.menu_item_location -> searchCurrentLocation()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupAppbar() {
        setHasOptionsMenu(true)

        val hostActivity = requireActivity() as AppCompatActivity
        hostActivity.setSupportActionBar(toolbarCurrentSnapshot)
        hostActivity.setupActionBarWithNavController(findNavController())
        hostActivity.supportActionBar?.title = ""
        hostActivity.supportActionBar?.setDisplayShowTitleEnabled(true)
        hostActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        hostActivity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        collapsingToolbarForecast.setCollapsedTitleTextColor(resources.getColor(R.color.colorPrimary, requireContext().theme))

        // Add offset change listeners to control title display and fading of views depending on scroll position of the appbar layout
        appbar.addOnOffsetChangedListener(TitleOnCollapseAppbarOffsetListener(collapsingToolbarForecast, object: ToolbarTitleDisplayProvider {
            override fun getTitle(): String {
                return weatherApplication.currentLocationName
            }
        })
        )
        appbar.addOnOffsetChangedListener(FadeLayoutAppBarOffsetChangedListener(FadeLayoutAppBarOffsetChangedListener.FadeMode.FADE_IN, frameOverlay, 1f))
        appbar.addOnOffsetChangedListener(FadeLayoutAppBarOffsetChangedListener(FadeLayoutAppBarOffsetChangedListener.FadeMode.FADE_OUT, layoutCurrentSnapshotHeader, 1.5f))
    }

    private fun setupViewModel() {
        if (favouriteLocationId == null) {
            currentSnapshotViewModel.requestSnapshot(currentPlace)
        } else {
            currentSnapshotViewModel.requestSnapshotForFavourite(favouriteLocationId!!)
        }
    }

    private fun setupRecycler() {
        recyclerViewDailySnapshot.addItemDecoration(
            DividerItemDecorator(ContextCompat.getDrawable(requireContext(), R.drawable.divider_vertical)!!, DividerItemDecorator.ViewOrientation.HORIZONTAL)
        )

        recyclerViewDailySnapshot.adapter = weekForecastAdapter
        recyclerViewDailySnapshot.layoutManager = GridLayoutManager(requireContext(), 7)
    }

    private fun setupBindings(view: View) {
        currentSnapshotDetailsBinding = FragmentCurrentSnapshotBinding.bind(view)
        currentSnapshotDetailsBinding.lifecycleOwner = viewLifecycleOwner
        currentSnapshotDetailsBinding.currentSnapshotBackdrop = R.drawable.backdrop_gradient_rainy
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
            requestPermissions(
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
                currentSnapshotViewModel.requestSnapshot(currentPlace)
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
            currentSnapshotDetailsBinding.currentSnapshotIcon = WeatherIconConverter.getIconIdForDescription(snapshot.icon)
            weekForecastAdapter.setDailySnapshots(snapshot.dailySnapshots)

            progressBar.visibility = View.GONE
            layoutCurrentSnapshotHeader.visibility = View.VISIBLE
        }

        displayResultError(result)
    }

    /**
     * Update data binding fields with readable location summary information from the viewmodel
     */
    private fun displayLocationSummary(result: Result<LocationSummary>) {
        val locationData = result.resultData

        if (locationData != null) {
            currentSnapshotViewModel.saveFavouriteLocation(locationData)
            weatherApplication.apply {
                currentLocationName = locationData.name
                currentLocationLatitude = locationData.latitude
                currentLocationLongitude = locationData.longitude
            }
            currentSnapshotDetailsBinding.location = locationData.name
        }

        displayResultError(result)
    }

    /**
     *
     */
    private fun searchCurrentLocation() {
        currentPlace = null
        currentSnapshotViewModel.requestSnapshot(null)
    }

    /**
     * Use places autocomplete to search for a location for which to obtain a forecast
     */
    private fun openLocationSearch() {
        val intent = PlacesHelper.getAutocompleteIntent(requireContext())
        startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE)
    }

    /**
     * Handle results from the places autocomplete to trigger a search via the view model
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_AUTOCOMPLETE && data != null) {
            if (resultCode == RESULT_OK) {
                currentPlace = Autocomplete.getPlaceFromIntent(data)
                currentSnapshotViewModel.requestSnapshot(currentPlace!!)

            } else if (resultCode == RESULT_ERROR) {
                PlacesHelper.handleAutocompleteError(requireContext(), data)
            }
        }
    }
}
