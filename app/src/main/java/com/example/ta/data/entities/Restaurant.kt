package com.example.ta.data.entities

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "restaurant")
data class Restaurant(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") var status: String,
    @Embedded @SerializedName("sortingValues") var sortingValues: SortingValues
) {
    @Ignore
    var isFav: Boolean = false

    data class SortingValues(
        @SerializedName("bestMatch") var bestMatch: Float,
        @SerializedName("newest")var newest: Float,
        @SerializedName("ratingAverage")var ratingAverage: Float,
        @SerializedName("distance")var distance: Int,
        @SerializedName("popularity")var popularity: Float,
        @SerializedName("averageProductPrice")var averageProductPrice: Int,
        @SerializedName("deliveryCosts")var deliveryCosts: Int,
        @SerializedName("minCost")var minCost: Int
    )
}
