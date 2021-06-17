package com.github.yasukotelin.jetpack_compose_sample.ui.screen

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.yasukotelin.jetpack_compose_sample.ui.compose.Center
import com.github.yasukotelin.jetpack_compose_sample.viewmodel.WebViewModel

@Composable
fun WebViewScreen(
    url: String,
    webViewModel: WebViewModel = viewModel(),
) {
    AndroidView({ context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    return false
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    webViewModel.onPageFinished()
                }
            }
            loadUrl(url)
        }
    })

    if (webViewModel.isLoading) {
        Center(modifier = Modifier.padding(top = 16.dp)) {
            CircularProgressIndicator()
        }
    }
}