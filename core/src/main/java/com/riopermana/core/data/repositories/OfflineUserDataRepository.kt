package com.riopermana.core.data.repositories

import com.riopermana.core.data.datastore.PreferencesDataSource
import com.riopermana.core.data.datastore.model.UserData
import com.riopermana.core.model.DarkThemeConfig
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineUserDataRepository @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource
) : UserDataRepository {

    override val userDataFlow: Flow<UserData>
        get() = preferencesDataSource.userDataFlow

    override suspend fun setDarkThemeConfig(config: DarkThemeConfig) {
        preferencesDataSource.setDarkThemeConfig(config)
    }

    override suspend fun toggleFavoriteRepo(repoId: Int, isFavorite: Boolean) {
        preferencesDataSource.toggleFavoriteRepo(repoId,isFavorite)
    }
}