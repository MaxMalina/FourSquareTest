package com.example.foresquaretask.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse (

    @SerializedName("groups")
    val groups: List<Group>
)

data class Group (

    @SerializedName("type")
    val type: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("items")
    val items: List<Item>
)

data class Item (
    @SerializedName("venue")
    val place: Place
)
