package com.dscreate_app.vkclient.presentation.screens.main.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dscreate_app.vkclient.domain.usecases.CheckAuthStateUseCase
import com.dscreate_app.vkclient.domain.usecases.GetAuthStateUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor (
    private val getAuthStateFlowUseCase: GetAuthStateUseCase,
    private val checkAuthStateUseCase: CheckAuthStateUseCase
): ViewModel() {

    val authState = getAuthStateFlowUseCase()

    fun performAuthResult() {
       viewModelScope.launch {
           checkAuthStateUseCase()
       }
    }
}