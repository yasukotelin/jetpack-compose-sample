package com.github.yasukotelin.jetpack_compose_sample.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.yasukotelin.jetpack_compose_sample.model.Repository
import com.github.yasukotelin.jetpack_compose_sample.model.User
import com.github.yasukotelin.jetpack_compose_sample.ui.compose.Center
import com.github.yasukotelin.jetpack_compose_sample.ui.theme.JetpackcomposesampleTheme
import com.github.yasukotelin.jetpack_compose_sample.viewmodel.HomeViewModel
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel(),
) {
    Scaffold(
        topBar = { TopAppBar() },
    ) {
        HomeScreenBody(
            user = homeViewModel.user,
            repositories = homeViewModel.repositories,
            onClickUserCard = {
                navController.navigate("web-view/?url=${homeViewModel.userPageUrl}")
            },
            onClickRepository = {
                navController.navigate("web-view/?url=${homeViewModel.getRepositoryPageUrl(it)}")
            }
        )
    }
}

@Composable
fun TopAppBar() {
    TopAppBar(
        title = { Text("Github Preview") },
    )
}

@Composable
fun HomeScreenBody(
    user: User?,
    repositories: List<Repository>,
    onClickUserCard: () -> Unit,
    onClickRepository: (repository: Repository) -> Unit,
) {
    if (user == null) {
        Center {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            Modifier
                .fillMaxSize()
        ) {
            item { UserCard(user = user, onClick = onClickUserCard) }

            item { Spacer(modifier = Modifier.size(16.dp)) }

            if (repositories.isEmpty()) {
                item { Center(modifier = Modifier.padding(top = 16.dp)) { CircularProgressIndicator() } }
            } else {
                repositories.forEach { repository ->
                    item { RepositoryItem(repository = repository, onClick = onClickRepository) }
                    item { Divider(Modifier.padding(start = 16.dp)) }
                }
            }
        }
    }
}

@Composable
private fun UserCard(
    user: User,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = onClick),
        elevation = 4.dp,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(end = 8.dp)
                    .size(80.dp),
                shape = CircleShape,
                elevation = 4.dp,
            ) {
                Image(
                    painter = rememberGlidePainter(request = user.avatarUrl),
                    contentDescription = "avatar url"
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(text = user.name, style = TextStyle(fontSize = 20.sp))
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = user.bio,
                    style = TextStyle(fontSize = 12.sp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = user.company, style = TextStyle(fontSize = 12.sp))
                Text(text = user.location, style = TextStyle(fontSize = 12.sp))
            }
        }
    }
}

@Composable
fun RepositoryItem(
    repository: Repository,
    onClick: (repository: Repository) -> Unit,
) {
    Surface(
        Modifier
            .fillMaxWidth()
            .clickable { onClick(repository) }
            .padding(horizontal = 16.dp)
            .height(60.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(repository.name, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
            Text(
                repository.description ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 12.sp, color = Color.Gray),
            )
        }
    }
}

@Preview
@Composable
fun UserCardPreview() {
    val user = User(
        name = "Name",
        avatarUrl = "https://placehold.jp/150x150.png",
        company = "@company",
        blog = "https://github.com/yasukotelin",
        location = "tokyo/japan",
        bio = "description",
        twitterUserName = "@yasukotelin"
    )
    JetpackcomposesampleTheme {
        Scaffold() {
            UserCard(user = user, onClick = {})
        }
    }
}
