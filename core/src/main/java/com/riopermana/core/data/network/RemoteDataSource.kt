package com.riopermana.core.data.network

import com.riopermana.core.data.network.model.GithubRepositoryNetwork

interface RemoteDataSource {
    fun getRepositories(query:String) : List<GithubRepositoryNetwork>
}