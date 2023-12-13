package com.dscreate_app.vkclient.navigation

sealed class Screens(
    val route: String
) {

    data object NewsFeed: Screens(ROUTE_NEWS_FEED)
    data object Favourite: Screens(ROUTE_FAVOURITE)
    data object Profile: Screens(ROUTE_PROFILE)
    data object Home: Screens(ROUTE_HOME)
    data object Comments: Screens(ROUTE_COMMENTS)

    private companion object {

        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments"
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVOURITE = "favourite"
        const val ROUTE_PROFILE = "profile"
    }
}