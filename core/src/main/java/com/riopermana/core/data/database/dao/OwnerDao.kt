package com.riopermana.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.riopermana.core.data.database.entities.OwnerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OwnerDao {
    @Query("SELECT * FROM owner")
    fun getRepositories() : Flow<List<OwnerEntity>>
}