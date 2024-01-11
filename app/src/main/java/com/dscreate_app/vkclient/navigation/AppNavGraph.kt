package com.dscreate_app.vkclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dscreate_app.vkclient.domain.entities.FeedPost

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsFeedScreenContent: @Composable () -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.Home.route
    ) {
        homeScreenNavGraph(
            newsFeedScreenContent,
            commentsScreenContent
        )
        composable(Screens.Favourite.route) {
            favouriteScreenContent()
        }
        composable(Screens.Profile.route) {
            profileScreenContent()
        }
    }
}