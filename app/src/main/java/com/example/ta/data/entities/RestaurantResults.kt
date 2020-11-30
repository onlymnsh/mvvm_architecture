package com.example.ta.data.entities

import com.google.gson.annotations.SerializedName

data class RestaurantResult(
    @SerializedName("restaurants") val restaurants: MutableList<Restaurant>
) {

}
