package com.github.yasukotelin.jetpack_compose_sample.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.yasukotelin.jetpack_compose_sample.data.remote.GithubApi
import com.github.yasukotelin.jetpack_compose_sample.model.Repository
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val githubApi: GithubApi,
) {
    suspend fun getUserRepository(user: String): Result<List<Repository>, ResponseError> {
        val r = githubApi.getUserRepository(user)
        return if (r.isSuccessful) {
            Ok(r.body() ?: listOf())
        } else {
            Err(
                ResponseError(
                    code = r.code(),
                    message = r.message()
                )
            )
        }
    }
}
