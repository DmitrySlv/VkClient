package com.dscreate_app.vkclient.presentation.screens.main.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dscreate_app.vkclient.data.repository.NewsFeedRepositoryImpl
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImpl(application)

    val authState = repository.authStateFlow

    fun performAuthResult() {
       viewModelScope.launch {
           repository.checkAuthState()
       }
    }
}