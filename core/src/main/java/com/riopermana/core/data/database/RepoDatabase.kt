package com.riopermana.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.riopermana.core.data.database.dao.RepoDao
import com.riopermana.core.data.database.entities.RepoMinimalEntity

@Database(
    version = 1,
    entities = [
        RepoMinimalEntity::class
    ]
)
abstract class RepoDatabase : RoomDatabase() {
    abstract val repoDao: RepoDao
}