package com.riopermana.core.model

import com.riopermana.core.data.database.entities.RepoMinimalEntity
import com.riopermana.core.data.network.model.OwnerNetwork
import com.riopermana.core.data.network.model.RepoNetwork

private fun OwnerNetwork.toMinimalModel() = Owner(
    avatarUrl = this.avatarUrl,
    htmlUrl = this.htmlUrl,
    id = this.id,
    login = this.login
)

internal fun RepoNetwork.toExternalMinimalModel() = Repo(
    description = this.description,
    forksCount = this.forksCount,
    fullName = this.fullName,
    htmlUrl = this.htmlUrl,
    id = this.id,
    language = this.language,
    name = this.name,
    owner = this.owner?.toMinimalModel(),
    watchersCount = this.watchersCount
)

internal fun RepoNetwork.toEntityMinimalModel() = RepoMinimalEntity(
    owner = this.owner?.toMinimalModel(),
    description = this.description,
    forksCount = this.forksCount,
    fullName = this.fullName,
    htmlUrl = this.htmlUrl,
    id = this.id,
    language = this.language,
    name = this.name,
    watchersCount = this.watchersCount
)

internal fun RepoMinimalEntity.toExternalMinimalModel() = Repo(
    description = this.description,
    forksCount = this.forksCount,
    fullName = this.fullName,
    htmlUrl = this.htmlUrl,
    id = this.id,
    language = this.language,
    name = this.name,
    owner = this.owner,
    watchersCount = this.watchersCount
)

