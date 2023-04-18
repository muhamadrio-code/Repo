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
    operator fun invoke(query: String) : Flow<Resource<List<FavoriteAbleRepo>>> {
        return combine(repoRepository.getRepos(query), userDataRepository.userDataFlow) { resource, userData ->
            val favoriteRepoIds = userData.favoriteRepoIds.map(String::toInt)
            val data = resource.data?.map { repo ->
                FavoriteAbleRepo(
                    repo = repo,
                    isFavorite = repo.id in favoriteRepoIds
                )
            }
            Resource(state = resource.state, data = data, throwable = resource.throwable)
        }
    }
}