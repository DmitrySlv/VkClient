package com.dscreate_app.vkclient.presentation.screens.comments

import com.dscreate_app.vkclient.domain.FeedPost
import com.dscreate_app.vkclient.domain.PostComment

sealed class CommentsScreenState {

    data object Initial: CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ): CommentsScreenState()
}