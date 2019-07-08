package com.example.foresquaretask.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

import com.example.foresquaretask.model.ApiResponse
import com.example.foresquaretask.repository.Repository

class PlaceViewModel(application: Application) : AndroidViewModel(application) {
    private val response:MediatorLiveData<ApiResponse> = MediatorLiveData()

    fun fetchPlaces(lat:String,lng:String):LiveData<ApiResponse>{
        response.addSource(Repository.fetchPlaces(lat, lng)){ data -> response.value = data }
        return response
    }
}