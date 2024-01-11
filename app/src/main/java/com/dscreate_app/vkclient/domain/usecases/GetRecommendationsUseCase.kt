package com.dscreate_app.vkclient.domain.usecases

import com.dscreate_app.vkclient.domain.entities.FeedPost
import com.dscreate_app.vkclient.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor (
    private val repository: NewsFeedRepository
) {

    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.getRecommendations()
    }
}