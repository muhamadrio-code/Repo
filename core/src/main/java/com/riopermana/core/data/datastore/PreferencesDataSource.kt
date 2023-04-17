package com.riopermana.core.data.datastore

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.riopermana.core.data.datastore.model.UserData
import com.riopermana.core.model.DarkThemeConfig
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_preferences")

class PreferencesDataSource @Inject constructor(
    context: Context
) {

    private val preferencesDataStore = context.dataStore

    private object PreferencesKeys {
        val DARK_THEME_CONFIG = intPreferencesKey("dark_theme_config")
    }

    val userData = preferencesDataStore.data.map { preferences ->
        UserData(
            darkThemeConfig = DarkThemeConfig.values().first {
                it.mode == (preferences[PreferencesKeys.DARK_THEME_CONFIG]
                    ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        )
    }

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        runCatching {
            preferencesDataStore.edit {
                it[PreferencesKeys.DARK_THEME_CONFIG] = darkThemeConfig.mode
            }
        }.onFailure {
            Timber.tag("Preferences").e("Failed to update user preferences")
        }
    }

}