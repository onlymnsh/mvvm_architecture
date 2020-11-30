package com.example.ta.utils

import androidx.lifecycle.LiveData
import com.example.ta.data.entities.Restaurant

class SortingUtility {
    companion object {
        fun SortRestaurant(
            data: LiveData<MutableList<Restaurant>>,
            sortBy: Sorting
        ): LiveData<MutableList<Restaurant>> {
            return when (sortBy) {
                Sorting.BEST_MATCH ->
                    data.also {
                        data.value?.sortWith(compareBy<Restaurant> { it.isFav }.thenBy { it.status }
                            .thenBy { it.sortingValues.bestMatch })
                    }
                Sorting.NEWEST ->
                    data.also {
                        data.value?.sortWith(compareBy<Restaurant> { it.isFav }.thenBy { it.status }
                            .thenBy { it.sortingValues.newest })
                    }
                Sorting.RATING_AVERAGE ->
                    data.also {
                        data.value?.sortWith(compareBy<Restaurant> { it.isFav }.thenBy { it.status }
                            .thenBy { it.sortingValues.ratingAverage })
                    }
                Sorting.DISTANCE ->
                    data.also {
                        data.value?.sortWith(compareBy<Restaurant> { it.isFav }.thenBy { it.status }
                            .thenBy { it.sortingValues.distance })
                    }
                Sorting.POPULARITY ->
                    data.also {
                        data.value?.sortWith(compareBy<Restaurant> { it.isFav }.thenBy { it.status }
                            .thenBy { it.sortingValues.popularity })
                    }
                Sorting.AVERAGE_PRODUCT_PRICE ->
                    data.also {
                        data.value?.sortWith(compareBy<Restaurant> { it.isFav }.thenBy { it.status }
                            .thenBy { it.sortingValues.averageProductPrice })
                    }
                Sorting.DELIVERY_COST ->
                    data.also {
                        data.value?.sortWith(compareBy<Restaurant> { it.isFav }.thenBy { it.status }
                            .thenBy { it.sortingValues.deliveryCosts })
                    }
                Sorting.MIN_COST ->
                    data.also {
                        data.value?.sortWith(compareBy<Restaurant> { it.isFav }.thenBy { it.status }
                            .thenBy { it.sortingValues.minCost })
                    }
            }
        }
    }
}

enum class Sorting {
    BEST_MATCH,
    NEWEST,
    RATING_AVERAGE,
    DISTANCE,
    POPULARITY,
    AVERAGE_PRODUCT_PRICE,
    DELIVERY_COST,
    MIN_COST
}