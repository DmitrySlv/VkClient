package com.dscreate_app.vkclient.navigation

import com.dscreate_app.vkclient.domain.FeedPost

sealed class Screens(
    val route: String
) {

    data object NewsFeed: Screens(ROUTE_NEWS_FEED)
    data object Favourite: Screens(ROUTE_FAVOURITE)
    data object Profile: Screens(ROUTE_PROFILE)
    data object Home: Screens(ROUTE_HOME)

    data object Comments: Screens(ROUTE_COMMENTS) {
        private const val ROUTE_FOR_ARG = "comments"

        fun getRouteWithArgs(feedPost: FeedPost): String {
            return "$ROUTE_FOR_ARG/${feedPost.id}/${feedPost.contentText}"
        }
     }

     companion object {
        const val KEY_FEED_POST_ID = "feed_post_id"
        const val KEY_CONTENT_TEXT = "content_text"

        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST_ID}/{$KEY_CONTENT_TEXT}"
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVOURITE = "favourite"
        const val ROUTE_PROFILE = "profile"
    }
}