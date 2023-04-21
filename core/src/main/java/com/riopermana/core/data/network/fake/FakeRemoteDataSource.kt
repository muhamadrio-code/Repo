package com.riopermana.core.data.network.fake

import com.google.gson.Gson
import com.riopermana.core.data.network.RemoteDataSource
import com.riopermana.core.data.network.helper.JvmUnitTestFakeAssetManager
import com.riopermana.core.data.network.model.RepoNetwork
import com.riopermana.core.data.network.model.ResponseData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeRemoteDataSource @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val gson: Gson,
    private val assetsManager: FakeAssetManager = JvmUnitTestFakeAssetManager
) : RemoteDataSource {

    companion object {
        const val REPOSITORIES = "github_repositories.json"
    }

    override suspend fun getRepositories(query: String): List<RepoNetwork> =
        withContext(dispatcher) {
            assetsManager.open(REPOSITORIES).use { reader ->
                gson.fromJson(reader, ResponseData::class.java)
            }.items
        }

}