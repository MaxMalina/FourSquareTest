package com.example.foresquaretask.ui

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import kotlinx.android.synthetic.main.place.view.*

import com.example.foresquaretask.model.Place
import com.example.foresquaretask.R

class PlaceAdapter(private var mDataList: List<Place>, private var mMap:MapboxMap):RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.place, parent, false)
        return PlaceViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = mDataList[position]
        holder.itemView.name.text = place.name
        var information = place.location.address
        place.categories.forEach {
            information += "\n" + it.shortName
        }
        holder.itemView.information.text = information
        holder.itemView.setOnClickListener {
            val selectedLocationLatLng = LatLng(mDataList[position].location.lat, mDataList[position].location.lng)
            val newCameraPosition = CameraPosition.Builder()
                .target(selectedLocationLatLng)
                .build()

            mMap.addMarker(MarkerOptions().setPosition(selectedLocationLatLng))
            mMap.easeCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition))
        }
    }

    inner class PlaceViewHolder(view: View) : RecyclerView.ViewHolder(view)
}