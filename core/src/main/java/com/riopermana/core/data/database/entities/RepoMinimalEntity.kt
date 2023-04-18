package com.riopermana.core.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.riopermana.core.model.Owner

@Entity("repositories", primaryKeys = ["id"])
data class RepoMinimalEntity(
    @Embedded(prefix = "owner_")
    val owner: Owner?,
    @ColumnInfo("description")
    val description: String?,
    @ColumnInfo("forks_count")
    val forksCount: Int?,
    @ColumnInfo("full_name")
    val fullName: String?,
    @ColumnInfo("html_url")
    val htmlUrl: String?,
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("language")
    val language: String?,
    @ColumnInfo("name")
    val name: String?,
    @ColumnInfo("watchers_count")
    val watchersCount: Int?,
    @ColumnInfo("stargazzers_count")
    val stargazersCount: Int?
)
