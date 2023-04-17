package com.riopermana.core.data.repositories

import com.riopermana.core.data.datastore.model.UserData
import com.riopermana.core.model.DarkThemeConfig
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userDataFlow: Flow<UserData>
    suspend fun setDarkThemeConfig(config:DarkThemeConfig)
    suspend fun toggleFavoriteRepo(repoId: Int, isFavorite: Boolean)
}