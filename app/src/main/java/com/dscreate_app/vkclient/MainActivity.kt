package com.dscreate_app.vkclient

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
                val someState = remember {
                    mutableStateOf(true)
                }
                Log.d("MainActivity", "Recomposition: ${someState.value}")
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
                // Вызывается только при первой успешной рекомпозиции, при первом изменении key и
                // при перевороте экрана. Вызывается в coroutine scope
                LaunchedEffect(key1 = someState.value) {
                   Log.d("MainActivity", "LaunchedEffect")
                }
                // Вызывается при каждой успешной рекомпозиции. Вызов не в coroutine scope.
                SideEffect {
                    Log.d("MainActivity", "SideEffect")
                }
                Button(onClick = { someState.value = !someState.value }) {
                    Text(text = "Change State")
                }
            }
        }
    }
}