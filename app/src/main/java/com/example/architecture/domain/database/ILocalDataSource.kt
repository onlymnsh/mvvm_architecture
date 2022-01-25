package com.example.architecture.domain.database

import com.example.architecture.domain.model.User

interface ILocalDataSource {

    suspend fun saveUserByDataStore(user: User)

    suspend fun getUserByDataStore(): User

    suspend fun saveUserByRoom(user: User)

    suspend fun getUserByRoom(): User
}