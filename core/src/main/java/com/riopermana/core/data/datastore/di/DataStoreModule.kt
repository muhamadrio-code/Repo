package com.riopermana.core.data.datastore.di

import android.content.Context
import com.riopermana.core.data.datastore.PreferencesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesPreferencesDataSource(
        @ApplicationContext context: Context
    ) = PreferencesDataSource(context)
}