package com.dscreate_app.vkclient.domain.usecases

import com.dscreate_app.vkclient.domain.repository.NewsFeedRepository
import javax.inject.Inject

class LoadNextDataUseCase @Inject constructor (
    private val repository: NewsFeedRepository
) {

   suspend operator fun invoke() {
        repository.loadNextData()
    }
}