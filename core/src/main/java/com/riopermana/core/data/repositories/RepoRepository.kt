package com.riopermana.core.data.repositories

import com.riopermana.core.data.Resource
import com.riopermana.core.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    fun getRepos(query: String): Flow<Resource<List<Repo>>>
    fun getRepo(id:Int): Flow<Resource<Repo>>
}