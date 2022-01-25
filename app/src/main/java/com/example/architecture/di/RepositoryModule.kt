package com.example.architecture.di

import com.example.architecture.domain.database.LocalDataSource
import com.example.architecture.domain.repository.LoginRepository
import com.example.architecture.domain.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesLoginRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ) =
        LoginRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )


}