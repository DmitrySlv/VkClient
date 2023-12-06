package com.dscreate_app.vkclient

import android.util.Log
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()
    val fabIsVisible = remember {
        mutableStateOf(true)
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
                        if (fabIsVisible.value) {
                            FloatingActionButton(
                                shape = CircleShape,
                                onClick = {
                                    scope.launch { // Запуск снэкбара только из корутины для компоуза
                                        val action = snackbarHostState.showSnackbar(
                                            message = "Это snackbar",
                                            actionLabel = "Спрятать FAB", //Кнопка в snackbar для скрытия snackbar
                                            duration = SnackbarDuration.Long //длительность snackbar
                                        )
                                        if (action == SnackbarResult.ActionPerformed) {
                                            fabIsVisible.value = false
                                        }
                                    }
                                }
                            ) {
                                Icon(Icons.Filled.Favorite, contentDescription = null)
                            }
                        }
        },
        bottomBar = {
            NavigationBar {
                Log.d("MyLog", "NavigationBar")

                val selectedItemPos = remember {
                    mutableStateOf(0)
                }

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemPos.value == index,
                        onClick = { selectedItemPos.value = index },
                        icon = { 
                            Icon(item.icon , contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        }
                    )
                }
            }
        }
    ) {

    }
}