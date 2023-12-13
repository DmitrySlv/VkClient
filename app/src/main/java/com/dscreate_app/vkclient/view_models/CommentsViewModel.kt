package com.dscreate_app.vkclient.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dscreate_app.vkclient.CommentsScreenState
import com.dscreate_app.vkclient.domain.FeedPost
import com.dscreate_app.vkclient.domain.PostComment

class CommentsViewModel(
    feedPost: FeedPost
): ViewModel() {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(feedPost)
    }

    private fun loadComments(feedPost: FeedPost) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(id = it))
            }
        }
        _screenState.value = CommentsScreenState.Comments(
            feedPost = feedPost, comments = comments
        )
    }
}