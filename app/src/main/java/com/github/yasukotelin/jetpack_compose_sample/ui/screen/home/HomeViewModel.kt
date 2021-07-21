package com.github.yasukotelin.jetpack_compose_sample.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.mapBoth
import com.github.yasukotelin.jetpack_compose_sample.model.Repository
import com.github.yasukotelin.jetpack_compose_sample.model.User
import com.github.yasukotelin.jetpack_compose_sample.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    private val userName = "yasukotelin"
    val userPageUrl get() = "https://github.com/$userName"

    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> get() = _user

    private var _repositories: MutableStateFlow<List<Repository>> = MutableStateFlow(listOf())
    val repositories: StateFlow<List<Repository>> get() = _repositories

    init {
        fetchData()
    }

    fun getRepositoryPageUrl(repository: Repository): String {
        return "https://github.com/$userName/${repository.name}"
    }

    private fun fetchData() = viewModelScope.launch {
        githubRepository.getUser(userName).flatMap {
            _user.emit(it)
            githubRepository.getUserRepository(userName)
        }.mapBoth(
            success = { _repositories.emit(it) },
            failure = {}
        )
    }
}