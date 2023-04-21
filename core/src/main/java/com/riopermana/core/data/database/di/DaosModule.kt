package com.riopermana.core.data.database.di

import com.riopermana.core.data.database.RepoDatabase
import com.riopermana.core.data.database.dao.RepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Singleton
    @Provides
    fun provideRepoDao(database: RepoDatabase) : RepoDao = database.repoDao
}