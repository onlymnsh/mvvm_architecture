package com.example.architecture.di

import com.example.architecture.domain.repository.LoginRepository
import com.example.architecture.features.datastore.usecase.DataStoreUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun providesDataStoreUseCase(repository: LoginRepository) =
        DataStoreUseCase(repository = repository)


}