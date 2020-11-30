package com.example.ta.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ta.data.entities.Restaurant

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant")
    fun getAllRestaurants() : LiveData<MutableList<Restaurant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurant: Restaurant)

    @Delete
    suspend fun delete(restaurant: Restaurant)


}