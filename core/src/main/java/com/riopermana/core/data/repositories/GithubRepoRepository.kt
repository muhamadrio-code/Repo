package com.riopermana.core.data.repositories

import androidx.room.withTransaction
import com.riopermana.core.data.database.RepoDatabase
import com.riopermana.core.data.database.entities.RepoMinimalEntity
import com.riopermana.core.data.network.RemoteDataSource
import com.riopermana.core.data.network.model.RepoNetwork
import com.riopermana.core.model.Repo
import com.riopermana.core.model.toEntityMinimalModel
import com.riopermana.core.model.toExternalMinimalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubRepoRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val database: RepoDatabase
) : RepoRepository {

    override fun getRepos(query: String): Flow<List<Repo>> {
        return flow {
            val networkRepos = remoteDataSource.getRepositories(query)
            database.withTransaction {
                with(database.repoDao) {
                    insertOrReplaceRepos(networkRepos.map(RepoNetwork::toEntityMinimalModel))
                    val ids = networkRepos.map { it.id }
                    emitAll(getReposMinimal(ids).map { it.map(RepoMinimalEntity::toExternalMinimalModel) })
                }
            }
        }
    }

    override fun getRepo(id:Int): Flow<Repo> {
        return flow {
            with(database.repoDao){
                emitAll(getRepoMinimal(id).map(RepoMinimalEntity::toExternalMinimalModel))
            }
        }
    }
}