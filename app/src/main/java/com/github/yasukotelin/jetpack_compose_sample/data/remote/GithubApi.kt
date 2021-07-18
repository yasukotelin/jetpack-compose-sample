package com.github.yasukotelin.jetpack_compose_sample.data.remote

import com.github.yasukotelin.jetpack_compose_sample.model.Repository
import com.github.yasukotelin.jetpack_compose_sample.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/users/{user}")
    suspend fun getUser(@Path("user") user: String): Response<User>

    @GET("users/{user}/repos?per_page=100")
    suspend fun getUserRepository(@Path("user") user: String): Response<List<Repository>>
}