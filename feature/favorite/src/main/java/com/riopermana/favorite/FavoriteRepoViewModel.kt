package com.riopermana.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riopermana.core.domain.ToggleFavoriteRepoUseCase
import com.riopermana.core.model.FavoriteAbleRepo
import kotlinx.coroutines.launch


class FavoriteRepoViewModel (
    getFavoriteReposUseCase: GetFavoriteReposUseCase,
    private val toggleFavoriteRepoUseCase: ToggleFavoriteRepoUseCase
) : ViewModel() {
    fun toggleFavoriteRepo(favoriteAbleRepo: FavoriteAbleRepo) = viewModelScope.launch {
        toggleFavoriteRepoUseCase(favoriteAbleRepo.repo.id, favoriteAbleRepo.isFavorite)
    }

    val favoriteUserResource = getFavoriteReposUseCase()

}