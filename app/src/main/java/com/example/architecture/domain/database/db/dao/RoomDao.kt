package com.example.architecture.domain.database.db.dao

import com.example.architecture.domain.model.User
import javax.inject.Inject

class RoomDao @Inject constructor(
    private val userDao: IUserDao,
    private val adminDao: IAdminDao
) {
    fun saveUser(user: User) = userDao.saveUser(user)

    fun getUser() = userDao.getUser()
}