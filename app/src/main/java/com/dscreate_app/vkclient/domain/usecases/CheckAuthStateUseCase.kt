package com.dscreate_app.vkclient.domain.usecases

import com.dscreate_app.vkclient.domain.repository.NewsFeedRepository

class CheckAuthStateUseCase(
    private val repository: NewsFeedRepository
) {

   suspend operator fun invoke() {
        repository.checkAuthState()
    }
}