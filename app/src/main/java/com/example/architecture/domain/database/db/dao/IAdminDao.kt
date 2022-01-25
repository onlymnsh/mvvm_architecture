package com.example.architecture.domain.database.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.architecture.domain.model.Admin

@Dao
interface IAdminDao {

    @Query("SELECT * FROM admin")
    fun getAdmin(): List<Admin>
}