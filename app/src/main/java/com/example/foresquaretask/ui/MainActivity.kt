package com.example.foresquaretask.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import kotlinx.android.synthetic.main.activity_main.*

import com.example.foresquaretask.R
import com.example.foresquaretask.model.ApiResponse
import com.example.foresquaretask.model.Place
import com.example.foresquaretask.repository.Data.TMP_LAT
import com.example.foresquaretask.repository.Data.TMP_LNG
import com.example.foresquaretask.viewmodels.PlaceViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PlaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(PlaceViewModel::class.java)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapboxMap ->
            viewModel.fetchPlaces(TMP_LAT, TMP_LNG).observe(this, Observer {
                val placeAdapter = PlaceAdapter(setUpPlaces(it, mapboxMap), mapboxMap)
                recyclerView.layoutManager = LinearLayoutManager(
                    applicationContext,
                    LinearLayoutManager.HORIZONTAL, true
                )
                recyclerView.itemAnimator = DefaultItemAnimator()
                recyclerView.adapter = placeAdapter
                val snapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(recyclerView)
            })
        }
    }

    // Add the mapView lifecycle to the activity's lifecycle methods
    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    public override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    public override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    private fun setUpPlaces(apiResponse: ApiResponse, mapboxMap: MapboxMap): ArrayList<Place> {
        val placesList = ArrayList<Place>()

        val items = apiResponse.response.groups[0].items
        items.forEach {
            placesList.add(it.place)

            val coordinate = LatLng(it.place.location.lat, it.place.location.lng)
            mapboxMap.addMarker(MarkerOptions().position(coordinate))
        }

        return placesList
    }
}
