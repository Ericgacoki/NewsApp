package io.github.joelkanyi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import io.github.joelkanyi.presentation.model.NewsUiModel
import io.github.joelkanyi.presentation.newsdetails.NewsDetails
import io.github.joelkanyi.presentation.newsdetails.NewsDetailsScreen
import io.github.joelkanyi.presentation.newslist.NewsList
import io.github.joelkanyi.presentation.newslist.NewsListScreen
import io.github.joelkanyi.presentation.search.SearchNews
import io.github.joelkanyi.presentation.search.SearchNewsScreen
import kotlin.reflect.typeOf

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NewsList,
    ) {
        composable<NewsList> {
            NewsListScreen(
                navController = navController,
            )
        }

        composable<NewsDetails>(
            typeMap = mapOf(typeOf<NewsUiModel>() to NewsUiModelParameterType)
        ) { backStackEntry ->
            val news = backStackEntry.toRoute<NewsDetails>().news
            NewsDetailsScreen(
                news = news,
                navController = navController,
            )
        }

        composable<SearchNews> {
            SearchNewsScreen(
                navController = navController,
            )
        }
    }
}
