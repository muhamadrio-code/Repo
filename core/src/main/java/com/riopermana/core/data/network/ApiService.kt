package com.riopermana.core.data.network

import com.riopermana.core.data.network.model.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("/search/repositories")
    fun getRepositories(
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 50,
        @Query("page") page: Int = 1,
    ) : Response<ResponseData>
}