package com.riopermana.core.data.network

import com.riopermana.core.data.network.model.RepoNetwork
import javax.inject.Inject


class RetrofitRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun getRepositories(query:String): List<RepoNetwork> {
        return apiService.getRepositories(query = query).body()?.items ?: emptyList()
    }
}