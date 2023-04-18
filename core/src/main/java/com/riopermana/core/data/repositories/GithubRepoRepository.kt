package com.riopermana.core.data.repositories

import androidx.room.withTransaction
import com.riopermana.core.data.Resource
import com.riopermana.core.data.ResourceState
import com.riopermana.core.data.database.RepoDatabase
import com.riopermana.core.data.database.entities.RepoMinimalEntity
import com.riopermana.core.data.network.RemoteDataSource
import com.riopermana.core.data.network.model.RepoNetwork
import com.riopermana.core.model.Repo
import com.riopermana.core.model.toEntityMinimalModel
import com.riopermana.core.model.toExternalMinimalModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class GithubRepoRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val database: RepoDatabase
) : RepoRepository {

    override fun getRepos(query: String): Flow<Resource<List<Repo>>> {
        return channelFlow {
            send(Resource(ResourceState.Loading))
            runCatching {
                val networkRepos = remoteDataSource.getRepositories(query)
                database.withTransaction {
                    with(database.repoDao) {
                        insertOrReplaceRepos(networkRepos.map(RepoNetwork::toEntityMinimalModel))
                        val ids = networkRepos.map(RepoNetwork::id)
                        val queryResult = getReposMinimal(ids)
                            .first()
                            .map(RepoMinimalEntity::toExternalMinimalModel)
                        send(Resource(state = ResourceState.Success, data = queryResult))
                    }
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
            with(database.repoDao) {
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
            with(database.repoDao){
                runCatching {
                    val data = getReposMinimal(ids).first().map(RepoMinimalEntity::toExternalMinimalModel)
                    emit(Resource(state = ResourceState.Success, data = data))
                }.onFailure {
                    emit(Resource(state = ResourceState.Error, throwable = it))
                }
            }
        }
    }
}

