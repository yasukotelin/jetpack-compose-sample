package com.github.yasukotelin.jetpack_compose_sample.ui.screen.webview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class WebViewModel @Inject constructor() : ViewModel() {

    private var _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun onPageFinished() = viewModelScope.launch {
        _isLoading.emit(false)
    }
}