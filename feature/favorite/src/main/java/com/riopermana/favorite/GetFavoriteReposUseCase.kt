package com.riopermana.favorite

import com.riopermana.core.data.Resource
import com.riopermana.core.data.repositories.RepoRepository
import com.riopermana.core.data.repositories.UserDataRepository
import com.riopermana.core.model.FavoriteAbleRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class)
class GetFavoriteReposUseCase(
    private val reposRepository: RepoRepository,
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(): Flow<Resource<List<FavoriteAbleRepo>>> {
        return userDataRepository
            .userDataFlow
            .map {
                it.favoriteRepoIds.map(String::toInt)
            }
            .flatMapLatest {
                reposRepository.getReposByIds(it).map { resource ->
                    val data = resource.data?.map { repo ->
                        FavoriteAbleRepo(
                            repo = repo, isFavorite = true
                        )
                    }

                    Resource(data = data, state = resource.state, throwable = resource.throwable)
                }
            }
    }
}