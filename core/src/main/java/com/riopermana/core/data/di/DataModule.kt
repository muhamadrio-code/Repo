package com.riopermana.core.data.di

import com.riopermana.core.data.repositories.GithubRepoRepository
import com.riopermana.core.data.repositories.OfflineUserDataRepository
import com.riopermana.core.data.repositories.RepoRepository
import com.riopermana.core.data.repositories.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindsRepoRepository(repoRepository: GithubRepoRepository) : RepoRepository

    @Binds
    @Singleton
    fun bindsUserDataRepository(userDataRepository: OfflineUserDataRepository) : UserDataRepository
}