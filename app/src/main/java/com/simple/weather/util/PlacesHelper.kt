package com.simple.weather.util

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.simple.weather.BuildConfig
import java.util.*

object PlacesHelper {

    /**
     * Set up the api client for Google Places
     */
    fun initialisePlaces(context: Context) {
        Places.initialize(context, BuildConfig.PlacesApiKey, Locale.getDefault())
        Places.createClient(context)
    }

    /**
     * Build an intent for the places autocomplete activity
     */
    fun getAutocompleteIntent(context: Context): Intent {
        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        val fields: List<Place.Field> = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)

        // Start the autocomplete intent.
        return Autocomplete.IntentBuilder(
            AutocompleteActivityMode.OVERLAY, fields)
            .setTypeFilter(TypeFilter.CITIES)
            .build(context)
    }

    /**
     * Handle errors returned from the places autocomplete activity
     */
    fun handleAutocompleteError(context: Context, intent: Intent) {
        val status = Autocomplete.getStatusFromIntent(intent)
        val toast = Toast.makeText(context, "Autocomplete error: ${status.statusMessage}", Toast.LENGTH_SHORT)
        toast.show()
    }
}