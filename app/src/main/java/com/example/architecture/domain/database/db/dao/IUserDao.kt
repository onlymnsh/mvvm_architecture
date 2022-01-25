package com.example.architecture.domain.database.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.architecture.domain.model.User

@Dao
interface IUserDao {

    @Insert
    fun saveUser(user: User)

    @Query("SELECT * FROM user")
    fun getUser():User
}