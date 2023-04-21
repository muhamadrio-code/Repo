package com.riopermana.core.data.repositories.testdoubles

import com.riopermana.core.data.database.dao.RepoDao
import com.riopermana.core.data.database.entities.RepoMinimalEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class TestRepoDao : RepoDao {

    private val entityStateFlow = MutableStateFlow(emptySet<RepoMinimalEntity>())

    override suspend fun insertOrReplaceRepos(repoEntities: List<RepoMinimalEntity>) {
        entityStateFlow.update { oldValue ->
            oldValue + repoEntities
        }
    }

    override fun getReposMinimal(ids: List<Int>): Flow<List<RepoMinimalEntity>> {
        return entityStateFlow.map { value ->
            value.filter {
                it.id in ids
            }
        }
    }

    override fun getRepoMinimal(id: Int): Flow<RepoMinimalEntity> {
        return entityStateFlow.map { value ->
            value.first { it.id == id }
        }
    }
}