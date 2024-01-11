package com.dscreate_app.vkclient.domain.usecases

import com.dscreate_app.vkclient.domain.entities.FeedPost
import com.dscreate_app.vkclient.domain.repository.NewsFeedRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor (
    private val repository: NewsFeedRepository
) {

   suspend operator fun invoke(feedPost: FeedPost) {
        repository.deletePost(feedPost)
    }
}