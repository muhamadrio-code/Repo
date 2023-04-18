package com.riopermana.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riopermana.core.data.repositories.RepoRepository
import com.riopermana.core.data.repositories.UserDataRepository
import com.riopermana.core.domain.ToggleFavoriteRepoUseCase
import javax.inject.Inject

class FavoriteFeatureViewModelFactory @Inject constructor(
    private val reposRepository: RepoRepository,
    private val userDataRepository: UserDataRepository,
    private val toggleFavoriteRepoUseCase: ToggleFavoriteRepoUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val getFavoriteReposUseCase = GetFavoriteReposUseCase(reposRepository, userDataRepository)
        return FavoriteRepoViewModel(getFavoriteReposUseCase, toggleFavoriteRepoUseCase) as T
    }
}