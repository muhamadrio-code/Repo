package com.riopermana.core.data.repositories.testdoubles

import com.google.gson.GsonBuilder
import com.riopermana.core.data.network.RemoteDataSource
import com.riopermana.core.data.network.fake.FakeRemoteDataSource
import com.riopermana.core.data.network.model.RepoNetwork
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class TestRepoRemoteDataSource : RemoteDataSource {

    private val source = FakeRemoteDataSource(
        UnconfinedTestDispatcher(),
        gson = GsonBuilder().serializeNulls().create()
    )

    override suspend fun getRepositories(query: String): List<RepoNetwork> =
        source.getRepositories(query)
}