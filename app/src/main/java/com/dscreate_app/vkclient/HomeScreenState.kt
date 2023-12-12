package com.dscreate_app.vkclient

import com.dscreate_app.vkclient.domain.FeedPost
import com.dscreate_app.vkclient.domain.PostComment

sealed class HomeScreenState {

    data object Initial: HomeScreenState()

    data class Posts(val posts: List<FeedPost>): HomeScreenState()
    data class Comments(val feedPost: FeedPost, val comments: List<PostComment>): HomeScreenState()
}