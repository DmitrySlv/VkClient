package com.dscreate_app.vkclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.dscreate_app.vkclient.ui.theme.VkClientTheme
import com.dscreate_app.vkclient.view_models.NewsFeedViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkClientTheme {
                ActivityResultTest()
            }
        }
    }
}