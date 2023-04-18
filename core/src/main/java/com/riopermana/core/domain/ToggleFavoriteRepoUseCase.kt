package com.riopermana.core.domain

import com.riopermana.core.data.repositories.UserDataRepository
import javax.inject.Inject

class ToggleFavoriteRepoUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
){
    suspend operator fun invoke(repoId: Int, isFavorite: Boolean) {
        userDataRepository.toggleFavoriteRepo(repoId, isFavorite)
    }
}