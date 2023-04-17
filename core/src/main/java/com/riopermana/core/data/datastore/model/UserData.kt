package com.riopermana.core.data.datastore.model

import com.riopermana.core.model.DarkThemeConfig

data class UserData(
    val darkThemeConfig: DarkThemeConfig,
    val favoriteRepoIds: Set<String>
)
