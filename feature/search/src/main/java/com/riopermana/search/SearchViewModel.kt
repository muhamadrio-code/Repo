package com.riopermana.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riopermana.core.domain.GetFavoriteAbleRepoUseCase
import com.riopermana.core.domain.ToggleFavoriteRepoUseCase
import com.riopermana.core.model.FavoriteAbleRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    getFavoriteAbleRepoUseCase: GetFavoriteAbleRepoUseCase,
    private val toggleFavoriteRepoUseCase: ToggleFavoriteRepoUseCase
) : ViewModel() {

    private val query = MutableStateFlow("language:kotlin")
    val resourceStateFlow = query
        .flatMapLatest(getFavoriteAbleRepoUseCase::invoke)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )

    fun getRepos(query: String) {
        this.query.value = query
    }

    fun toggleFavoriteRepo(favoriteAbleRepo: FavoriteAbleRepo) = viewModelScope.launch {
        toggleFavoriteRepoUseCase(favoriteAbleRepo.repo.id, favoriteAbleRepo.isFavorite)
    }
}