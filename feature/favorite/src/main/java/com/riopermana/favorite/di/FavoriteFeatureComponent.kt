package com.riopermana.favorite.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.riopermana.core.di.FavoriteFeatureCoreDependencies
import com.riopermana.favorite.FavoriteRepoFragment
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.android.EntryPointAccessors

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