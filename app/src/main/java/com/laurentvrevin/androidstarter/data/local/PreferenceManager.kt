package com.laurentvrevin.androidstarter.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/**
 * Gestionnaire réactif des préférences utilisateur utilisant DataStore.
 * 
 * Contrairement à SharedPreferences, DataStore offre une API asynchrone 
 * basée sur les [Flow], évitant ainsi de bloquer le thread principal.
 */
class PreferenceManager(private val context: Context) {

    suspend fun saveString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[prefKey] = value
        }
    }

    fun getString(key: String, defaultValue: String = ""): Flow<String> {
        val prefKey = stringPreferencesKey(key)
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[prefKey] ?: defaultValue
            }
    }

    suspend fun saveBoolean(key: String, value: Boolean) {
        val prefKey = booleanPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[prefKey] = value
        }
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Flow<Boolean> {
        val prefKey = booleanPreferencesKey(key)
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[prefKey] ?: defaultValue
            }
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
