package com.dscreate_app.vkclient.presentation.screens.comments.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dscreate_app.vkclient.data.repository.NewsFeedRepositoryImpl
import com.dscreate_app.vkclient.domain.entities.FeedPost
import com.dscreate_app.vkclient.domain.usecases.GetCommentsUseCase
import com.dscreate_app.vkclient.presentation.screens.comments.CommentsScreenState
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
): ViewModel() {

    private val repository = NewsFeedRepositoryImpl(application)

    private val getCommentsUseCase = GetCommentsUseCase(repository)

    val screenState =getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}