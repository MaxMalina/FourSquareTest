package com.example.foresquaretask

import android.app.Application
import com.mapbox.mapboxsdk.Mapbox

class FourSquareTaskApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Mapbox.getInstance(applicationContext, getString(R.string.mapbox_access_token))
    }
}