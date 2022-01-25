package com.example.architecture.domain.repository

import com.example.architecture.domain.database.LocalDataSource
import com.example.architecture.domain.interfaces.ILoginRepository
import com.example.architecture.domain.model.User
import com.example.architecture.domain.network.RemoteDataSource
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : ILoginRepository {
    override suspend fun saveUser(user: User) {
        localDataSource.saveUserByDataStore(user)
    }

    override suspend fun getUser(): User {
        return localDataSource.getUserByDataStore()
    }
}