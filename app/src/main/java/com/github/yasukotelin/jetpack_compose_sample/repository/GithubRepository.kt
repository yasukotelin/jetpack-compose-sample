package com.github.yasukotelin.jetpack_compose_sample.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.yasukotelin.jetpack_compose_sample.data.remote.GithubApi
import com.github.yasukotelin.jetpack_compose_sample.model.Repository
import com.github.yasukotelin.jetpack_compose_sample.model.User
import retrofit2.Response
import java.lang.IllegalStateException
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val githubApi: GithubApi,
) {
    suspend fun getUser(user: String): Result<User?, ResponseError> =
        githubApi.getUser(user).result()

    suspend fun getUserRepository(user: String): Result<List<Repository>, ResponseError> =
        githubApi.getUserRepository(user).result()

    private fun <T> Response<T>.result(): Result<T, ResponseError> {
        return if (isSuccessful) {
            Ok(body() ?: throw IllegalStateException("response body is null"))
        } else {
            Err(
                ResponseError(
                    code = code(),
                    message = message()
                )
            )
        }
    }
}
