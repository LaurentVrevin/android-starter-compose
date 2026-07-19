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
 * Abstraction typée pour les préférences de l'application.
 * Remplace l'accès par clés String par des méthodes explicites.
 */
class AppPreferences(private val context: Context) {

    private object Keys {
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val LAST_SYNC = longPreferencesKey("last_sync")
    }

    val isDarkTheme: Flow<Boolean?> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { preferences -> preferences[Keys.DARK_THEME] }

    suspend fun setDarkTheme(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[Keys.DARK_THEME] = enabled
        }
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
