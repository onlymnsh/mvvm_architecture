package com.example.architecture.domain.network

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val iServices: IServices
) {

    suspend fun get() {
        iServices.getLanguages()
    }


}