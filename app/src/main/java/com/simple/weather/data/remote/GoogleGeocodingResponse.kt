package com.simple.weather.data.remote

import com.simple.weather.data.models.LocationSummary

object GoogleGeocodingResponse {

    /**
     * Data classes to align with the API provided by Dark Sky - https://darksky.net/
     */

    data class GeocodingResponse(
        val results: List<GeocodingResult>,
        val status: String
    )

    data class GeocodingResult(
        val address_components: List<AddressComponent>,
        val geometry: Geometry
    )

    data class AddressComponent(
        val long_name: String,
        val short_name: String,
        val types: List<String>
    )

    data class Geometry(
        val location: GeoLocation
    )

    data class GeoLocation(
        val lat: Double,
        val lng: Double
    )

    /**
     * Converter function to map api response object to internal model
     */
    fun GeocodingResponse.toLocationSummary(): LocationSummary {
        val result = results.first()
        val geoLocation = results.first().geometry.location
        var locationName = ""

        // Find the address components for locality
        for (component in result.address_components) {
            if (component.types.contains("locality")) {
                locationName = component.short_name
            }
        }

        return LocationSummary(geoLocation.lat, geoLocation.lng, locationName)
    }
}