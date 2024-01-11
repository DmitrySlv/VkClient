package com.dscreate_app.vkclient.presentation.screens.comments.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dscreate_app.vkclient.domain.entities.FeedPost

class CommentsViewModelFactory(
    private val feedPost: FeedPost,
    private val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost, application) as T
    }
}