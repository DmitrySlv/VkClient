package com.dscreate_app.vkclient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dscreate_app.vkclient.domain.FeedPost
import com.dscreate_app.vkclient.view_models.CommentsViewModel

class CommentsViewModelFactory(
    private val feedPost: FeedPost
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost) as T
    }
}