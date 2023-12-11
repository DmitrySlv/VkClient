package com.dscreate_app.vkclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.NewsFeed.route
    ) {
        composable(Screens.NewsFeed.route) {
           homeScreenContent()
        }
        composable(Screens.Favourite.route) {
            favouriteScreenContent()
        }
        composable(Screens.Profile.route) {
            profileScreenContent()
        }
    }
}