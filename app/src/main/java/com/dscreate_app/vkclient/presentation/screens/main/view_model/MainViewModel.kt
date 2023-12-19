package com.dscreate_app.vkclient.presentation.screens.main.view_model

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dscreate_app.vkclient.presentation.screens.main.AuthState
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthenticationResult

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val _authState = MutableLiveData<AuthState>(AuthState.Initial)
    val authState: LiveData<AuthState> = _authState

    init {
        val storage = VKPreferencesKeyValueStorage(application)
        val token = VKAccessToken.restore(storage)
        //токен хранится в sharedPref, т.к получение/сохран токена делается быстро и других нет доступа к нему.
        // Только в приложении. Минус: можно увидеть token, если получить root доступ.
        val loggedIn = token != null && token.isValid
        Log.d("MyLog", "Token: ${token?.accessToken}") // Выдает в лог текущ токен
        _authState.value = if (loggedIn) AuthState.Authorized else AuthState.NotAuthorized
    }

    fun performAuthResult(result: VKAuthenticationResult) {
        if (result is VKAuthenticationResult.Success) {
            _authState.value = AuthState.Authorized
        } else {
            val error = (result as VKAuthenticationResult.Failed).exception
            _authState.value = AuthState.NotAuthorized
            Log.d("MainViewModel", "Unsuccess: $error")
        }
    }
}