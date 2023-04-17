package com.riopermana.core.data.network

import com.riopermana.core.data.network.model.RepoNetwork

interface RemoteDataSource {
    suspend fun getRepositories(query:String) : List<RepoNetwork>
}