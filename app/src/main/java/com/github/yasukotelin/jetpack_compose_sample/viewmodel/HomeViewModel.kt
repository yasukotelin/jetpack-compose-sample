package com.github.yasukotelin.jetpack_compose_sample.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.mapBoth
import com.github.yasukotelin.jetpack_compose_sample.model.Repository
import com.github.yasukotelin.jetpack_compose_sample.model.User
import com.github.yasukotelin.jetpack_compose_sample.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    private val userName = "yasukotelin"

    var user: User? by mutableStateOf(null)
        private set

    var repositories: List<Repository> by mutableStateOf(listOf())
        private set

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        githubRepository.getUser(userName).flatMap {
            user = it
            githubRepository.getUserRepository(userName)
        }.mapBoth(
            success = { repositories = it },
            failure = {}
        )
    }
}