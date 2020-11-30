package com.example.ta.di

import android.content.Context
import com.example.ta.data.local.AppDatabase
import com.example.ta.data.local.RestaurantDao
import com.example.ta.data.repository.RestaurantRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideRestaurantDao(db: AppDatabase) = db.restaurantDao()

    @Singleton
    @Provides
    fun provideRepository(localDataSource: RestaurantDao) = RestaurantRepository(localDataSource)
}