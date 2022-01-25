package com.example.architecture.di

import android.content.Context
import androidx.room.Room
import com.example.architecture.domain.database.LocalDataSource
import com.example.architecture.domain.database.datastore.DataStoreManager
import com.example.architecture.domain.database.db.AppDatabase
import com.example.architecture.domain.database.db.dao.RoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {

    @Provides
    @Singleton
    fun providesLocalDataSource(dataStoreManager: DataStoreManager, roomDao: RoomDao) =
        LocalDataSource(dataStoreManager, roomDao)


    @Provides
    @Singleton
    fun providesDataStoreManager(@ApplicationContext context: Context) = DataStoreManager(context)


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "App_Database"
        ).build()

    @Provides
    fun provideRoomDao(appDatabase: AppDatabase): RoomDao {
        return RoomDao(appDatabase.userDao(), appDatabase.adminDao())
    }

}