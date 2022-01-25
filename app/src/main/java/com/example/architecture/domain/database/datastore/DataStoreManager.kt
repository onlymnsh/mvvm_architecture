package com.example.architecture.domain.database.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStoreManager @Inject constructor(appContext: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "App_DataStore")

    val localDataStore = appContext.dataStore

    /**
     * puts a key value pair in Data Store if doesn't exists, otherwise updates value on given [key]
     */
    suspend inline fun <reified T> set(key: String, value: T?) {
        when (value) {
            is String -> localDataStore.edit { it[stringPreferencesKey(key)] = value }
            is Int -> localDataStore.edit { it[intPreferencesKey(key)] = value }
            is Boolean -> localDataStore.edit { it[booleanPreferencesKey(key)] = value }
            is Float -> localDataStore.edit { it[floatPreferencesKey(key)] = value }
            is Long -> localDataStore.edit { it[longPreferencesKey(key)] = value }
            is Double -> localDataStore.edit { it[doublePreferencesKey(key)] = value }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    inline fun <reified T> get(
        key: String,
        defaultValue: T? = null
    ): Flow<T?> {
        return localDataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            when (T::class) {
                String::class -> it[stringPreferencesKey(key)] ?: defaultValue
                Int::class -> it[intPreferencesKey(key)] ?: defaultValue
                Boolean::class -> it[booleanPreferencesKey(key)] ?: defaultValue
                Float::class -> it[floatPreferencesKey(key)] ?: defaultValue
                Long::class -> it[longPreferencesKey(key)] ?: defaultValue
                Double::class -> it[doublePreferencesKey(key)] ?: defaultValue
                else -> throw UnsupportedOperationException("Not yet implemented")
            }

        } as Flow<T?>
    }

}

