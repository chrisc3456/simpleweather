package com.simple.weather.ui.favouritelocations

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.simple.weather.R
import com.simple.weather.data.models.FavouriteLocationSummary
import com.simple.weather.data.models.Result
import com.simple.weather.ui.common.BaseFragment
import com.simple.weather.ui.common.DividerItemDecorator
import kotlinx.android.synthetic.main.fragment_favourite_locations.*
import javax.inject.Inject

class FavouriteLocationsFragment : BaseFragment() {

    private val adapter = FavouriteLocationsAdapter()

    @Inject
    lateinit var viewModel: FavouriteLocationsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        weatherApplication.weatherComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favourite_locations, container, false)

        setupViewModel()
        addObservers()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()

        // Set the navigation controller from the activity manually as this fragment is embedded within the drawer layout rather than within the nav host
        Navigation.setViewNavController(view, requireActivity().findNavController(R.id.navHostMain))
    }

    private fun setupRecycler() {
        recyclerFavourites.addItemDecoration(
            DividerItemDecorator(ContextCompat.getDrawable(requireContext(), R.drawable.divider_horizontal_narrow)!!, DividerItemDecorator.ViewOrientation.VERTICAL)
        )

        recyclerFavourites.adapter = adapter
        recyclerFavourites.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupViewModel() {
        viewModel.requestFavouriteLocations()
    }

    private fun addObservers() {
        viewModel.favouriteLocations.observe(viewLifecycleOwner, Observer { result ->
            displayFavouriteLocations(result)
        })
    }

    /**
     * Update data binding fields with location summary information from the viewmodel
     */
    private fun displayFavouriteLocations(result: Result<List<FavouriteLocationSummary>>) {
        val locations = result.resultData

        if (locations != null) {
            adapter.setFavourites(locations)
        }

        displayResultError(result)
    }
}
