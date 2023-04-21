package com.riopermana.core.data.network.fake

import com.google.gson.GsonBuilder
import com.riopermana.core.data.network.model.OwnerNetwork
import com.riopermana.core.data.network.model.RepoNetwork
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FakeRemoteDataSourceTest {

    private lateinit var subject: FakeRemoteDataSource
    private val dispatcher = StandardTestDispatcher()
    private val query = "language:kotlin"

    @Before
    fun setUp() {
        subject = FakeRemoteDataSource(
            dispatcher = dispatcher,
            gson = GsonBuilder().serializeNulls().create()
        )
    }

    @Test
    fun `test deserialization response`() = runTest(dispatcher) {
        val expected = RepoNetwork(
            id = 3432266,
            nodeId = "MDEwOlJlcG9zaXRvcnkzNDMyMjY2",
            name = "kotlin",
            fullName = "JetBrains/kotlin",
            owner = OwnerNetwork(
                login = "JetBrains",
                id = 878437,
                nodeId = "MDEyOk9yZ2FuaXphdGlvbjg3ODQzNw==",
                avatarUrl = "https://avatars.githubusercontent.com/u/878437?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/JetBrains",
                htmlUrl = "https://github.com/JetBrains",
                followersUrl = "https://api.github.com/users/JetBrains/followers",
                followingUrl = "https://api.github.com/users/JetBrains/following{/other_user}",
                gistsUrl = "https://api.github.com/users/JetBrains/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/JetBrains/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/JetBrains/subscriptions",
                organizationsUrl = "https://api.github.com/users/JetBrains/orgs",
                reposUrl = "https://api.github.com/users/JetBrains/repos",
                eventsUrl = "https://api.github.com/users/JetBrains/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/JetBrains/received_events",
                type = "Organization",
                siteAdmin = false
            ),
            htmlUrl = "https://github.com/JetBrains/kotlin",
            description = "The Kotlin Programming Language. ",
            fork = false,
            url = "https://api.github.com/repos/JetBrains/kotlin",
            forksUrl = "https://api.github.com/repos/JetBrains/kotlin/forks",
            keysUrl = "https://api.github.com/repos/JetBrains/kotlin/keys{/key_id}",
            collaboratorsUrl = "https://api.github.com/repos/JetBrains/kotlin/collaborators{/collaborator}",
            teamsUrl = "https://api.github.com/repos/JetBrains/kotlin/teams",
            hooksUrl = "https://api.github.com/repos/JetBrains/kotlin/hooks",
            issueEventsUrl = "https://api.github.com/repos/JetBrains/kotlin/issues/events{/number}",
            eventsUrl = "https://api.github.com/repos/JetBrains/kotlin/events",
            assigneesUrl = "https://api.github.com/repos/JetBrains/kotlin/assignees{/user}",
            branchesUrl = "https://api.github.com/repos/JetBrains/kotlin/branches{/branch}",
            tagsUrl = "https://api.github.com/repos/JetBrains/kotlin/tags",
            blobsUrl = "https://api.github.com/repos/JetBrains/kotlin/git/blobs{/sha}",
            gitTagsUrl = "https://api.github.com/repos/JetBrains/kotlin/git/tags{/sha}",
            gitRefsUrl = "https://api.github.com/repos/JetBrains/kotlin/git/refs{/sha}",
            treesUrl = "https://api.github.com/repos/JetBrains/kotlin/git/trees{/sha}",
            statusesUrl = "https://api.github.com/repos/JetBrains/kotlin/statuses/{sha}",
            languagesUrl = "https://api.github.com/repos/JetBrains/kotlin/languages",
            stargazersUrl = "https://api.github.com/repos/JetBrains/kotlin/stargazers",
            contributorsUrl = "https://api.github.com/repos/JetBrains/kotlin/contributors",
            subscribersUrl = "https://api.github.com/repos/JetBrains/kotlin/subscribers",
            subscriptionUrl = "https://api.github.com/repos/JetBrains/kotlin/subscription",
            commitsUrl = "https://api.github.com/repos/JetBrains/kotlin/commits{/sha}",
            gitCommitsUrl = "https://api.github.com/repos/JetBrains/kotlin/git/commits{/sha}",
            commentsUrl = "https://api.github.com/repos/JetBrains/kotlin/comments{/number}",
            issueCommentUrl = "https://api.github.com/repos/JetBrains/kotlin/issues/comments{/number}",
            contentsUrl = "https://api.github.com/repos/JetBrains/kotlin/contents/{+path}",
            compareUrl = "https://api.github.com/repos/JetBrains/kotlin/compare/{base}...{head}",
            mergesUrl = "https://api.github.com/repos/JetBrains/kotlin/merges",
            archiveUrl = "https://api.github.com/repos/JetBrains/kotlin/{archive_format}{/ref}",
            downloadsUrl = "https://api.github.com/repos/JetBrains/kotlin/downloads",
            issuesUrl = "https://api.github.com/repos/JetBrains/kotlin/issues{/number}",
            pullsUrl = "https://api.github.com/repos/JetBrains/kotlin/pulls{/number}",
            milestonesUrl = "https://api.github.com/repos/JetBrains/kotlin/milestones{/number}",
            notificationsUrl = "https://api.github.com/repos/JetBrains/kotlin/notifications{?since,all,participating}",
            labelsUrl = "https://api.github.com/repos/JetBrains/kotlin/labels{/name}",
            releasesUrl = "https://api.github.com/repos/JetBrains/kotlin/releases{/id}",
            deploymentsUrl = "https://api.github.com/repos/JetBrains/kotlin/deployments",
            createdAt = "2012-02-13T17:29:58Z",
            updatedAt = "2023-04-21T00:56:52Z",
            pushedAt = "2023-04-20T22:47:15Z",
            gitUrl = "git://github.com/JetBrains/kotlin.git",
            sshUrl = "git@github.com:JetBrains/kotlin.git",
            cloneUrl = "https://github.com/JetBrains/kotlin.git",
            svnUrl = "https://github.com/JetBrains/kotlin",
            homepage = "https://kotlinlang.org",
            size = 1888122,
            stargazersCount = 44457,
            watchersCount = 44457,
            language = "Kotlin",
            hasIssues = false,
            hasProjects = false,
            hasDownloads = true,
            hasWiki = false,
            hasPages = false,
            hasDiscussions = false,
            forksCount = 5500,
            mirrorUrl = null,
            archived = false,
            disabled = false,
            openIssuesCount = 149,
            allowForking = true,
            isTemplate = false,
            webCommitSignoffRequired = false,
            visibility = "public",
            forks = 5500,
            openIssues = 149,
            watchers = 44457,
            defaultBranch = "master",
            score = 1.0,
            isPrivate = false
        )

        assertEquals(
            expected,
            subject.getRepositories(query).first()
        )
    }
}