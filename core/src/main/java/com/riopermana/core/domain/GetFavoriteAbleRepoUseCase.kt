package com.riopermana.core.domain

import com.riopermana.core.data.Resource
import com.riopermana.core.data.repositories.RepoRepository
import com.riopermana.core.data.repositories.UserDataRepository
import com.riopermana.core.model.FavoriteAbleRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetFavoriteAbleRepoUseCase @Inject constructor(
    private val repoRepository: RepoRepository,
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(repoId: Int): Flow<Resource<FavoriteAbleRepo>> {
        return combine(
            repoRepository.getRepo(repoId),
            userDataRepository.userDataFlow
        ) { resource, userData ->
            val favoriteRepoIds = userData.favoriteRepoIds.map(String::toInt)
            val repo = resource.data

            if (repo != null){
                val data = FavoriteAbleRepo(
                    repo = repo,
                    isFavorite = repo.id in favoriteRepoIds
                )
                return@combine Resource(state = resource.state, data = data)
            } else {
                return@combine Resource(state = resource.state, data = null, resource.throwable)
            }
        }
    }
}