package com.dscreate_app.vkclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.dscreate_app.vkclient.ui.theme.VkClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkClientTheme {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.background)
//                        .padding(8.dp)
//                ) {
//                    PostCard()
//                }
                Test()
            }
        }
    }
}

@Composable
private fun Test() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Example3()
    }
}

@Composable
private fun Example1() {
    OutlinedButton(
        onClick = {  }
    ) {
        Text(text = "Hello World")
    }
}

@Composable
private fun Example2() {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(text = "Label") },
        singleLine = true
    )
}

@Composable
private fun Example3() {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Да"
            )
        },
        title = {
            Text(text = "Вы уверены?")
        },
        text = {
            Text(text = "Вы действительно хотите удалить этот файл?")
        },
        dismissButton = {
            TextButton(
                onClick = {
                }
            ) {
                Text(text = "Нет")
            }
        }
    )
}