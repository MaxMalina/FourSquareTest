package com.example.foresquaretask.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

import com.example.foresquaretask.model.ApiResponse

interface PlaceApi {
    @GET("explore")
    fun fetchPlaces(@QueryMap options:Map<String, String>): Call<ApiResponse>
}