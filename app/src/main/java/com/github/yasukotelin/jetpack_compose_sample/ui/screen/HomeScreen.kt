package com.github.yasukotelin.jetpack_compose_sample.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.yasukotelin.jetpack_compose_sample.model.Repository
import com.github.yasukotelin.jetpack_compose_sample.ui.theme.JetpackcomposesampleTheme
import com.github.yasukotelin.jetpack_compose_sample.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel(),
) {
    Scaffold() {
        RepositoryList(repositories = homeViewModel.repositories)
    }
}

@Composable
fun RepositoryList(repositories: List<Repository>) {
    LazyColumn {
        repositories.forEach { repository ->
            item {
                Text(repository.fullName)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackcomposesampleTheme {
    }
}