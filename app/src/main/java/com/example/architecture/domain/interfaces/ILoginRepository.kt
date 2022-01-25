package com.example.architecture.domain.interfaces

import com.example.architecture.domain.model.User

interface ILoginRepository {

    suspend fun saveUser(user: User)
    suspend fun getUser(): User
}