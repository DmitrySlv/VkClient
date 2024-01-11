package com.dscreate_app.vkclient.presentation.screens.comments.view_model

import androidx.lifecycle.ViewModel
import com.dscreate_app.vkclient.domain.entities.FeedPost
import com.dscreate_app.vkclient.domain.usecases.GetCommentsUseCase
import com.dscreate_app.vkclient.presentation.screens.comments.CommentsScreenState
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentsViewModel @Inject constructor (
    private val feedPost: FeedPost,
    private val getCommentsUseCase: GetCommentsUseCase
): ViewModel() {

    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}