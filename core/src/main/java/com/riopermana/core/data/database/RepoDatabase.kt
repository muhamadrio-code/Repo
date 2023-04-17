package com.riopermana.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.riopermana.core.data.database.dao.OwnerDao
import com.riopermana.core.data.database.dao.RepoDao
import com.riopermana.core.data.database.entities.RepoEntity
import com.riopermana.core.data.database.entities.OwnerEntity

@Database(
    version = 1,
    entities = [
        RepoEntity::class,
        OwnerEntity::class
    ]
)
abstract class RepoDatabase : RoomDatabase() {
    abstract val repoDao: RepoDao
    abstract val ownerDao: OwnerDao
}