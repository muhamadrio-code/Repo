package com.riopermana.core.model


data class Repo(
    val description: String?,
    val forksCount: Int?,
    val fullName: String?,
    val htmlUrl: String?,
    val id: Int,
    val language: String?,
    val name: String?,
    val owner: Owner?,
    val watchersCount: Int?,
    val stargazersCount: Int
)
