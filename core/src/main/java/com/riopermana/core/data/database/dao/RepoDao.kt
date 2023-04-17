package com.riopermana.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.riopermana.core.data.database.entities.RepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {

    @Query("SELECT * FROM repositories")
    fun getRepositories() : Flow<List<RepoEntity>>
}