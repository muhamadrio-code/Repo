package com.riopermana.core.data.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.riopermana.core.BuildConfig
import com.riopermana.core.data.network.ApiService
import com.riopermana.core.data.network.GithubRepoApiInterceptor
import com.riopermana.core.data.network.RemoteDataSource
import com.riopermana.core.data.network.RetrofitRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().serializeNulls().create()

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        val hostname = BuildConfig.BASE_URL.substringAfter("//")
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/1UPHAdcUbUoOcd5rDTD/0oMSnngCU6YzXzpByO4CCp4=")
            .add(hostname, "sha256/RQeZkB42znUfsDIIFWIRiYEcKl7nHwNFwWCrnMMJbVc=")
            .add(hostname, "sha256/Jg78dOE+fydIGk19swWwiypUSR6HWZybfnJG/8G7pyM=")
            .add(hostname, "sha256/e0IRz5Tio3GA1Xs4fUVWmH1xHDiH2dMbVtCBSkOIdqM=")
            .build()
        val httpLoggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        addInterceptor(httpLoggingInterceptor)
        addInterceptor(GithubRepoApiInterceptor())
        certificatePinner(certificatePinner)
    }.build()

    @Provides
    @Singleton
    fun provideApiService(
        client: OkHttpClient,
        gson: Gson
    ) : ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(
            GsonConverterFactory.create(gson)
        )
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        apiService: ApiService
    ): RemoteDataSource = RetrofitRemoteDataSource(apiService)
}