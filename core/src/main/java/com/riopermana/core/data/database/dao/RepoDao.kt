package com.riopermana.core.data.database.dao

import androidx.room.*
import com.riopermana.core.data.database.entities.RepoMinimalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceRepos(repoEntities: List<RepoMinimalEntity>)

    @Query("SELECT * FROM repositories WHERE id IN (:ids)")
    fun getReposMinimal(ids: List<Int>) : Flow<List<RepoMinimalEntity>>

    @Query("SELECT * FROM repositories WHERE id = :id")
    fun getRepoMinimal(id: Int) : Flow<RepoMinimalEntity>
}