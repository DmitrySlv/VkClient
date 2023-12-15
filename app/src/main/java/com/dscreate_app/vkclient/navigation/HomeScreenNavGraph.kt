package com.dscreate_app.vkclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
        composable(
            route = Screens.Comments.route,
            arguments = listOf( // указать тип передаваемого аргумента автоматически, чтобы не кастовать вручную.
                navArgument(Screens.KEY_FEED_POST) {
                    type = FeedPost.NavigationType
                }
            )
        ) { // сюда приходит строка вида //comments/{feed_post_id}
          val feedPost = it.arguments?.getParcelable<FeedPost>(Screens.KEY_FEED_POST) ?: throw RuntimeException("Args is null")
            commentsScreenContent(feedPost)
        }
    }
}