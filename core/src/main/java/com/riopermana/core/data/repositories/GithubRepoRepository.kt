package com.riopermana.core.data.repositories

import com.riopermana.core.data.Resource
import com.riopermana.core.data.ResourceState
import com.riopermana.core.data.database.dao.RepoDao
import com.riopermana.core.data.database.entities.RepoMinimalEntity
import com.riopermana.core.data.network.RemoteDataSource
import com.riopermana.core.data.network.model.RepoNetwork
import com.riopermana.core.model.Repo
import com.riopermana.core.model.toEntityMinimalModel
import com.riopermana.core.model.toExternalMinimalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class GithubRepoRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val repoDao: RepoDao
) : RepoRepository {

    override fun getRepos(query: String): Flow<Resource<List<Repo>>> {
        return channelFlow {
            runCatching {
                send(Resource(ResourceState.Loading))
                val networkRepos = remoteDataSource.getRepositories(query)
                with(repoDao) {
                    insertOrReplaceRepos(networkRepos.map(RepoNetwork::toEntityMinimalModel))
                    val ids = networkRepos.map(RepoNetwork::id)
                    val queryResult = getReposMinimal(ids)
                        .first()
                        .map(RepoMinimalEntity::toExternalMinimalModel)
                    send(Resource(state = ResourceState.Success, data = queryResult))
                }
            }.onFailure {
                Timber.tag("Error").e("Failed to get data")
                send(Resource(state = ResourceState.Error, throwable = it))
            }
        }
    }

    override fun getRepo(id: Int): Flow<Resource<Repo>> {
        return flow {
            emit(Resource(state = ResourceState.Loading))
            with(repoDao) {
                runCatching {
                    val data = getRepoMinimal(id).first().toExternalMinimalModel()
                    emit(Resource(state = ResourceState.Success, data = data))
                }.onFailure {
                    emit(Resource(state = ResourceState.Error, throwable = it))
                }
            }
        }
    }

    override fun getReposByIds(ids: List<Int>): Flow<Resource<List<Repo>>> {
        return flow {
            emit(Resource(state = ResourceState.Loading))
            with(repoDao) {
                runCatching {
                    val data =
                        getReposMinimal(ids).first().map(RepoMinimalEntity::toExternalMinimalModel)
                    emit(Resource(state = ResourceState.Success, data = data))
                }.onFailure {
                    emit(Resource(state = ResourceState.Error, throwable = it))
                }
            }
        }
    }
}

