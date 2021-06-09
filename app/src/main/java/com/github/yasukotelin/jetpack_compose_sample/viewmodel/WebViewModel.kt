package com.github.yasukotelin.jetpack_compose_sample.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class WebViewModel @Inject constructor() : ViewModel() {

    var isLoading: Boolean by mutableStateOf(true)
        private set

    fun onPageFinished() {
        isLoading = false
    }
}