package com.github.yasukotelin.jetpack_compose_sample.data.remote

import com.github.yasukotelin.jetpack_compose_sample.model.Repository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users/{user}/repos")
    suspend fun getUserRepository(@Path("user") user: String): Response<List<Repository>>
}