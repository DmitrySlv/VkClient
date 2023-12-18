package com.dscreate_app.vkclient

sealed class AuthState {

    data object Initial: AuthState()

    data object Authorized: AuthState()
    data object NotAuthorized: AuthState()

}