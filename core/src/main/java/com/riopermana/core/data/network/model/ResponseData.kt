package com.riopermana.core.data.network.model

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @field:SerializedName("items")
    val items: List<RepoNetwork>,
)
