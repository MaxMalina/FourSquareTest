package com.example.foresquaretask.model

import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("response")
    val response: PlaceResponse,

    @SerializedName("meta")
    val meta: Meta
)