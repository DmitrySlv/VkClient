package com.dscreate_app.vkclient.navigation

import android.net.Uri
import com.dscreate_app.vkclient.domain.entities.FeedPost
import com.google.gson.Gson

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
            val feedPostJson = Gson().toJson(feedPost) // передает целый объект в виде Json
            return "$ROUTE_FOR_ARG/${feedPostJson.encode()}"
        }
     }

     companion object {
        const val KEY_FEED_POST = "feed_post"

        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVOURITE = "favourite"
        const val ROUTE_PROFILE = "profile"
    }
}

//Uri.encode() передает строку полностью со всеми спец символами, если вдруг они будут в параметрах при передаче.
fun String.encode(): String {
    return Uri.encode(this)
}