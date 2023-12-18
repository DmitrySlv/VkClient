package com.dscreate_app.vkclient.presentation.screens.news

import com.dscreate_app.vkclient.domain.FeedPost

sealed class NewsFeedScreenState {

    data object Initial: NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>): NewsFeedScreenState()
}