package com.example.architecture.features.datastore.usecase

import com.example.architecture.domain.model.User
import com.example.architecture.domain.repository.LoginRepository
import javax.inject.Inject

class DataStoreUseCase @Inject constructor(
    private val repository: LoginRepository

) {

    suspend fun saveUser(user: User){
        repository.saveUser(user)
    }
    suspend fun getUser():User{
        return repository.getUser()
    }
}