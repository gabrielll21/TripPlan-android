package br.com.tripplan

import android.app.Application
import android.util.Log
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient

class TripPlanApplication : Application() {

    var placesClient: PlacesClient? = null
        private set

    override fun onCreate() {
        super.onCreate()

        val apiKey = BuildConfig.PLACES_API_KEY

        if (apiKey.isBlank() || apiKey == "DEFAULT_API_KEY") {
            Log.e(TAG, "PLACES_API_KEY não foi configurada")
            return
        }

        if (!Places.isInitialized()) {
            Places.initializeWithNewPlacesApiEnabled(applicationContext, apiKey)
        }

        placesClient = Places.createClient(this)
    }

    private companion object {
        const val TAG = "TripPlanApplication"
    }
}
