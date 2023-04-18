package com.riopermana.core.di

import com.riopermana.core.data.repositories.RepoRepository
import com.riopermana.core.data.repositories.UserDataRepository
import com.riopermana.core.domain.ToggleFavoriteRepoUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteFeatureCoreDependencies {

    fun repoRepository() : RepoRepository
    fun userDataRepository() : UserDataRepository
    fun toggleFavoriteRepo() : ToggleFavoriteRepoUseCase

}