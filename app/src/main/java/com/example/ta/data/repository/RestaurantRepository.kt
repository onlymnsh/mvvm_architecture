package com.example.ta.data.repository

import androidx.lifecycle.LiveData
import com.example.ta.data.entities.Restaurant
import com.example.ta.data.local.RestaurantDao
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val localDataSource: RestaurantDao
) {

    suspend fun saveRestaurant(restaurant: Restaurant) {
        localDataSource.insert(restaurant)
    }

    fun getAllFavRestaurant(): LiveData<MutableList<Restaurant>>{
        return localDataSource.getAllRestaurants()
    }

    suspend fun deleteFavRestaurant(restaurant: Restaurant){
        localDataSource.delete(restaurant)
    }

}