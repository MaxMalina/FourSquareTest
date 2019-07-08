package com.example.foresquaretask.model

import com.google.gson.annotations.SerializedName

data class Place (

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("location")
    val location: Location,

    @SerializedName("categories")
    val categories: List<Category>
)

data class Location (
    @SerializedName("address")
    val address: String,

    @SerializedName("lat")
    val lat: Double,

    @SerializedName("lng")
    val lng: Double,

    @SerializedName("distance")
    val distance: Int,

    @SerializedName("formattedAddress")
    val formattedAddress: List<String>
)

data class Category (
    @SerializedName("id")
    val id: String,

    @SerializedName("shortName")
    val shortName: String
)

