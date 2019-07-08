package com.example.foresquaretask.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.example.foresquaretask.model.ApiResponse
import com.example.foresquaretask.repository.Data.BASE_URL
import com.example.foresquaretask.repository.Data.CLIENT_ID
import com.example.foresquaretask.repository.Data.CLIENT_SECRET
import com.example.foresquaretask.repository.Data.LIMIT
import com.example.foresquaretask.repository.Data.VERSION

object Repository {

    private val TAG = Repository::class.java.simpleName

    private val api: PlaceApi
    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(PlaceApi::class.java)
    }

    fun fetchPlaces(lat:String, lng:String):LiveData<ApiResponse>{
        val data = MutableLiveData<ApiResponse>()

        val options = hashMapOf (
            "client_id" to CLIENT_ID,
            "client_secret" to CLIENT_SECRET,
            "v" to VERSION,
            "limit" to LIMIT,
            "ll" to "$lat, $lng"
        )

        api.fetchPlaces(options).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.body() != null)
                    data.postValue(response.body())
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e(TAG, "Network error occured! " + t.message)
            }
        })

        return data
    }
}