package com.dscreate_app.vkclient.domain.usecases

import com.dscreate_app.vkclient.domain.entities.AuthState
import com.dscreate_app.vkclient.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor (
    private val repository: NewsFeedRepository
) {

    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}