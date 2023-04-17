package com.riopermana.core.data.network

import com.riopermana.core.data.network.model.GithubRepositoryNetwork
import javax.inject.Inject


class RetrofitRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override fun getRepositories(query:String): List<GithubRepositoryNetwork> {
        return apiService.getRepositories(query = query).body()?.items ?: emptyList()
    }
}