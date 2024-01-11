package com.dscreate_app.vkclient.domain.usecases

import com.dscreate_app.vkclient.domain.repository.NewsFeedRepository

class LoadNextDataUseCase(
    private val repository: NewsFeedRepository
) {

   suspend operator fun invoke() {
        repository.loadNextData()
    }
}