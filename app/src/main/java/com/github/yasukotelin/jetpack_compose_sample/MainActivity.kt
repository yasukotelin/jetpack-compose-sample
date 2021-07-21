package com.github.yasukotelin.jetpack_compose_sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.github.yasukotelin.jetpack_compose_sample.ui.screen.home.HomeScreen
import com.github.yasukotelin.jetpack_compose_sample.ui.screen.webview.WebViewScreen
import com.github.yasukotelin.jetpack_compose_sample.ui.theme.JetpackcomposesampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackcomposesampleTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, hiltViewModel()) }
        composable(
            "web-view/?url={url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType }),
        ) { backStackEntry ->
            WebViewScreen(url = backStackEntry.arguments?.getString("url") ?: "")
        }
    }
}