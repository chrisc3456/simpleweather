package com.simple.weather.ui.common

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.simple.weather.SimpleWeatherApp
import com.simple.weather.data.models.Result
import com.simple.weather.ui.main.MainActivity

open class BaseFragment: Fragment() {
    lateinit var weatherApplication: SimpleWeatherApp

    override fun onAttach(context: Context) {
        super.onAttach(context)
        weatherApplication = requireActivity().application as SimpleWeatherApp
    }

    /**
     * Adds an inset listener for the provided view to override system UI flags and apply system insets for the top status bar
     */
    fun applyTopSystemInsetsToView(view: View) {
        view.setOnApplyWindowInsetsListener { v, insets ->
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.systemWindowInsetTop
            }
            insets
        }
    }

    /**
     * Adds an inset listener for the provided view to override system UI flags and apply system insets for the bottom navigation bar
     */
    fun applyBottomSystemInsetsToView(view: View) {
        view.setOnApplyWindowInsetsListener { v, insets ->
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = insets.systemWindowInsetBottom
            }
            insets
        }
    }

    /**
     * Opens/closes the activity drawer layout from a fragment
     */
    fun toggleDrawerState() {
        (requireActivity() as MainActivity).toggleDrawerState()
    }

    /**
     * Display details of any errors to the user
     */
    fun displayResultError(result: Result<*>) {
        if (result.state == Result.State.COMPLETE_ERROR) {
            val toast = Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG)
            toast.show()
        }
    }
}