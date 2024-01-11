package com.dscreate_app.vkclient.domain.usecases

import com.dscreate_app.vkclient.domain.entities.FeedPost
import com.dscreate_app.vkclient.domain.entities.PostComment
import com.dscreate_app.vkclient.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor (
    private val repository: NewsFeedRepository
) {

    operator fun invoke(feedPost: FeedPost): StateFlow<List<PostComment>> {
        return repository.getComments(feedPost)
    }
}