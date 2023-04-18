package com.riopermana.favorite.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.riopermana.core.data.repositories.GithubRepoRepository
import com.riopermana.core.data.repositories.OfflineUserDataRepository
import com.riopermana.core.data.repositories.RepoRepository
import com.riopermana.core.data.repositories.UserDataRepository
import com.riopermana.core.di.FavoriteFeatureCoreDependencies
import com.riopermana.favorite.FavoriteRepoFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

fun FavoriteRepoFragment.inject(context: Context) {
    DaggerFavoriteFeatureComponent.factory()
        .favoriteFeatureComponent(
            this,
            EntryPointAccessors.fromApplication(
                context.applicationContext,
                FavoriteFeatureCoreDependencies::class.java
            )
        )
        .inject(this)
}


@Component(
    dependencies = [FavoriteFeatureCoreDependencies::class]
)
interface FavoriteFeatureComponent {

    fun inject(fragment: FavoriteRepoFragment)

    @Component.Factory
    interface Factory {
        fun favoriteFeatureComponent(
            @BindsInstance fragment: Fragment,
            loginModuleDependencies: FavoriteFeatureCoreDependencies
        ): FavoriteFeatureComponent
    }
}