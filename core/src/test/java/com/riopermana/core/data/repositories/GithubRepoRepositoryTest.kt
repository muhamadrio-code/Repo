package com.riopermana.core.data.repositories

import com.riopermana.core.data.ResourceState
import com.riopermana.core.data.database.dao.RepoDao
import com.riopermana.core.data.database.entities.RepoMinimalEntity
import com.riopermana.core.data.repositories.testdoubles.TestRepoDao
import com.riopermana.core.data.repositories.testdoubles.TestRepoRemoteDataSource
import com.riopermana.core.model.toExternalMinimalModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class GithubRepoRepositoryTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())
    private lateinit var subject: RepoRepository
    private lateinit var repoDao: RepoDao
    private lateinit var network: TestRepoRemoteDataSource

    @Before
    fun setUp() {
        repoDao = TestRepoDao()
        network = TestRepoRemoteDataSource()
        subject = GithubRepoRepository(
            network, repoDao
        )
    }

    @Test
    fun `test data stream backed by RepoDao`() = testScope.runTest {
        val resource = subject.getRepos("").toList()
        val expected = resource.last().data

        val ids = network.getRepositories("").map { it.id }
        val actual = repoDao.getReposMinimal(ids).map {
            it.map(RepoMinimalEntity::toExternalMinimalModel)
        }.first()

        assertNotNull(expected)
        assertEquals(expected, actual)
    }

    @Test
    fun `test get Repo backed by RepoDao`() = testScope.runTest {
        subject.getRepos("").collect()

        val id = 3432266
        val resource = subject.getRepo(id)
        val expected = resource.toList().last().data

        val actual = repoDao.getRepoMinimal(id).first().toExternalMinimalModel()

        assertNotNull(expected)
        assertEquals(expected, actual)
    }

    @Test
    fun `test get data stream backed by RepoDao first state is Loading`() = testScope.runTest {
        val expected = subject.getRepos("").first()

        assertEquals(expected.state, ResourceState.Loading)
    }
}