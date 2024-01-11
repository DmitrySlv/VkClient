package com.dscreate_app.vkclient.presentation.screens.main.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dscreate_app.vkclient.data.repository.NewsFeedRepositoryImpl
import com.dscreate_app.vkclient.domain.usecases.CheckAuthStateUseCase
import com.dscreate_app.vkclient.domain.usecases.GetAuthStateUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImpl(application)

    private val getAuthStateFlowUseCase = GetAuthStateUseCase(repository)
    private val checkAuthStateUseCase = CheckAuthStateUseCase(repository)

    val authState = getAuthStateFlowUseCase()

    fun performAuthResult() {
       viewModelScope.launch {
           checkAuthStateUseCase()
       }
    }
}