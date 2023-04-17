package com.riopermana.core.data.network.di

import com.google.gson.GsonBuilder
import com.riopermana.core.BuildConfig
import com.riopermana.core.data.network.ApiService
import com.riopermana.core.data.network.GithubRepoApiInterceptor
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitFactoryConverter(): Converter.Factory = GsonConverterFactory.create(
        GsonBuilder().serializeNulls().create()
    )

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        val httpLoggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        addInterceptor(httpLoggingInterceptor)
        addInterceptor(GithubRepoApiInterceptor())
    }.build()

    @Provides
    @Singleton
    fun provideApiService(
        client: OkHttpClient,
        retrofitConverterFactory: Converter.Factory
    ) : ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(retrofitConverterFactory)
        .build()
        .create(ApiService::class.java)
}