package com.example.architecture.di

import android.content.Context
import com.example.architecture.domain.network.IServices
import com.example.architecture.domain.network.NetworkInterceptor
import com.example.architecture.domain.network.RemoteDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val URL = "https://www.google.com"

    @Provides
    @Singleton
    fun providesRemoteSource(service: IServices) = RemoteDataSource(service)


    @Provides
    @Singleton
    fun provideNetworkInterceptor(@ApplicationContext appContext: Context): NetworkInterceptor {
        return NetworkInterceptor(appContext)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideOkHttp(networkInterceptor: NetworkInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .callTimeout(45, TimeUnit.SECONDS)
            .addInterceptor(networkInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttp: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .client(okHttp)
        .baseUrl(URL)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): IServices = retrofit.create()
}