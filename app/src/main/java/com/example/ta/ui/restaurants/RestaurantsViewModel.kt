package com.example.ta.ui.restaurants

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.ta.data.entities.Restaurant
import com.example.ta.data.entities.RestaurantResult
import com.example.ta.data.repository.RestaurantRepository
import com.example.ta.utils.Utility
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
@ActivityScoped
class RestaurantsViewModel @ViewModelInject constructor(
    @ApplicationContext application: Context,
    private val repository: RestaurantRepository
) : ViewModel() {
    var appContext: Context = application
    var restaurants = getAllRestaurants()
    private fun getAllRestaurants(): LiveData<MutableList<Restaurant>> {
        if(restaurants?.value?.isNullOrEmpty()?.not() == true){
           restaurants.value?.clear()
        }
        val list = getRestaurantListFromJson().also { res -> res.sortWith(compareBy<Restaurant>{it.status}.thenBy { it.sortingValues.bestMatch }) }

        return getFavouritesListFromDb().also { fav ->
            fav.value?.forEach { item ->
                run {
                    item.isFav = true
                    list.removeAll { it.id == item.id }
                }
            }
            fav.value?.sortWith(compareBy<Restaurant>{it.status}.thenBy { it.sortingValues.bestMatch })
            fav.value?.addAll(list)
        }
    }

    private fun getFavouritesListFromDb(): LiveData<MutableList<Restaurant>>  {
        return repository.getAllFavRestaurant()
    }

    private fun getRestaurantListFromJson(): MutableList<Restaurant> {
        val jsonFileString = Utility.getJsonDataFromAsset(appContext, "sample.json")

        val restaurantType = object : TypeToken<RestaurantResult>() {}.type
        val restaurantResult: RestaurantResult = Gson().fromJson(jsonFileString, restaurantType)
        return   restaurantResult.restaurants
    }

    suspend fun saveFavouriteRestaurant(restaurantId: Int){
        restaurants.value?.first { it.id==restaurantId }?.let { repository.saveRestaurant(it) }
        restaurants = getAllRestaurants()
    }

    suspend fun deleteFavouriteRestaurant(restaurantId: Int){
        restaurants.value?.first { it.id==restaurantId }?.let { repository.deleteFavRestaurant(it) }
        restaurants = getAllRestaurants()
    }
}
