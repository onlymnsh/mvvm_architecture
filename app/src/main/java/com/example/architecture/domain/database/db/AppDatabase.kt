package com.example.architecture.domain.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.architecture.domain.database.db.dao.IAdminDao
import com.example.architecture.domain.database.db.dao.IUserDao
import com.example.architecture.domain.model.Admin
import com.example.architecture.domain.model.User

@Database(entities = [User::class, Admin::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): IUserDao
    abstract fun adminDao(): IAdminDao
}