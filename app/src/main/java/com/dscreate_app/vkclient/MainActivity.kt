package com.dscreate_app.vkclient

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.dscreate_app.vkclient.ui.theme.VkClientTheme
import com.dscreate_app.vkclient.view_models.NewsFeedViewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkClientTheme {
              val launcher =  rememberLauncherForActivityResult(contract = VK.getVKAuthActivityResultContract()) {
                    val authLauncher= VK.login(this) { result->
                        when (result) {
                            is VKAuthenticationResult.Success -> {
                                Log.d("MainActivity", "Success auth")
                            }
                            is VKAuthenticationResult.Failed -> {
                                Log.d("MainActivity", "Error auth")
                            }
                        }
                    }
                }
                launcher.launch(listOf(VKScope.WALL))
                MainScreen()
            }
        }
    }
}