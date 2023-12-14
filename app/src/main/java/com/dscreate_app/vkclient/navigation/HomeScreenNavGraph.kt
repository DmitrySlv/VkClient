package com.dscreate_app.vkclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dscreate_app.vkclient.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit
) {
    navigation(
        startDestination = Screens.NewsFeed.route,
        route = Screens.Home.route
    ) {
        composable(Screens.NewsFeed.route) {
            newsFeedScreenContent()
        }
        composable(Screens.Comments.route) {
          val feedPostId =  it.arguments?.getInt(Screens.KEY_FEED_POST_ID) ?: 0
            commentsScreenContent(FeedPost(id = feedPostId))
        }
    }
}