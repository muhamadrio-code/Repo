package com.riopermana.core.data.database.di

import android.content.Context
import androidx.room.Room
import com.riopermana.core.data.database.RepoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideUserDatabase(
        @ApplicationContext context: Context
    ) : RepoDatabase = Room.databaseBuilder(
        context,
        RepoDatabase::class.java,
        "repo-database.db"
    ).build()
}