package com.example.architecture.domain.network


import retrofit2.http.GET
interface IServices {
    @GET("APIConstant.LANGUAGE_API")
    suspend fun getLanguages(): List<String>


}