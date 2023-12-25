package com.dscreate_app.vkclient.presentation.screens.comments.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dscreate_app.vkclient.data.repository.NewsFeedRepositoryImpl
import com.dscreate_app.vkclient.domain.FeedPost
import com.dscreate_app.vkclient.domain.PostComment
import com.dscreate_app.vkclient.presentation.screens.comments.CommentsScreenState
import kotlinx.coroutines.launch

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
): ViewModel() {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    private val repository = NewsFeedRepositoryImpl(application)

    init {
        loadComments(feedPost)
    }

    private fun loadComments(feedPost: FeedPost) {
        viewModelScope.launch {
            val comments = repository.getComments(feedPost)
            _screenState.value = CommentsScreenState.Comments(
                feedPost = feedPost, comments = comments
            )
        }
    }
}