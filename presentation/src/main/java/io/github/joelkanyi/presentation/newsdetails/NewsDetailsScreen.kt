package io.github.joelkanyi.presentation.newsdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import io.github.joelkanyi.designsystem.theme.NewsAppTheme
import io.github.joelkanyi.presentation.model.NewsUiModel
import kotlinx.serialization.Serializable


@Serializable
data class NewsDetails(val news: NewsUiModel)

@Composable
fun NewsDetailsScreen(
    news: NewsUiModel,
    navController: NavController,
) {
    NewsDetailsScreenContent(
        news = news,
        onClickBack = {
            navController.navigateUp()
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsScreenContent(
    news: NewsUiModel,
    onClickBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        onClickBack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    Text(
                        text = news.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
            )
        },
    ) {
        Box(
            modifier =
            Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "News Details",
            )
        }
    }
}

@Preview
@Composable
fun NewsDetailsScreenPreview() {
    NewsAppTheme {
        NewsDetailsScreenContent(
            news = NewsUiModel(
                title = "Title",
                description = "Description",
                url = "Url",
                imageUrl = "UrlToImage",
                publishedAt = "PublishedAt",
                content = "Content",
                source = "Source",
                author = "Author",
            ),
            onClickBack = {},
        )
    }
}
