package com.riopermana.core.data.network

import com.riopermana.core.data.network.model.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val DEFAULT_PER_PAGE = 60

interface ApiService {
    @GET("/search/repositories")
    fun getRepositories(
        @Query("q") query: String,
        @Query("per_page") perPage: Int = DEFAULT_PER_PAGE,
        @Query("page") page: Int = 1,
    ) : Response<ResponseData>
}