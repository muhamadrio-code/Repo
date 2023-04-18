package com.riopermana.core.data

data class Resource<T>(
    val state: ResourceState,
    val data: T? = null,
    val throwable: Throwable? = null
)

sealed interface ResourceState {
    object Loading: ResourceState
    object Success: ResourceState
    object Error: ResourceState
}