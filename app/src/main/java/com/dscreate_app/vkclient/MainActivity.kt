package com.dscreate_app.vkclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dscreate_app.vkclient.screens.LoginScreen
import com.dscreate_app.vkclient.screens.MainScreen
import com.dscreate_app.vkclient.ui.theme.VkClientTheme
import com.dscreate_app.vkclient.view_models.MainViewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkClientTheme {
                val viewModel: MainViewModel = viewModel()
                val authState = viewModel.authState.observeAsState(AuthState.Initial)

                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract()
                ) {
                    viewModel.performAuthResult(it)
                }
                when (authState.value) {
                    AuthState.Authorized -> {
                        MainScreen()
                    }
                    AuthState.NotAuthorized -> {
                        LoginScreen {
                            launcher.launch(listOf(VKScope.WALL))
                        }
                    }
                    AuthState.Initial -> {}
                }
            }
        }
    }
}