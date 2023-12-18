package com.dscreate_app.vkclient.samples

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dscreate_app.vkclient.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer() {
    val items = listOf(
        DrawerItem(
            Icons.Default.Favorite,
            "Избранное"
        ),
        DrawerItem(
            Icons.Default.Add,
            "Добавить"
        ),
        DrawerItem(
            Icons.Default.AccountBox,
            "Аккаунт"
        )
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) // Меню закрыто по умолчанию.
    val scope = rememberCoroutineScope() // Для обновления UI откр/закрытия меню
    val selectedItem = remember { // Запоминает состояние кнопок
        mutableStateOf(items[0])
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { // Сохраняется контент меню
                        ModalDrawerSheet {
                            Image(
                                painter = painterResource(id = R.drawable.vk_logo),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            items.forEach { item ->
                                NavigationDrawerItem(
                                    label = {
                                            Text(text = item.title)
                                    },
                                    selected = selectedItem.value == item, // проверяет каждый элемент в меню
                                    icon = {
                                           Icon(
                                               imageVector = item.imageVector,
                                               contentDescription = item.title
                                           )
                                    },
                                    onClick = {
                                        scope.launch { // Для закрытия меню
                                            selectedItem.value = item
                                            drawerState.close()
                                        }
                                    }
                                )
                            }
                        }
        },
        content = { // Сюда добавляется весь контент, который открывается после закрытия меню (в данном случае topBar и bottomBar)
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "TopAppBar Title")
                        },
                        navigationIcon = {
                            IconButton(onClick = { }) {
                                Icon(
                                    Icons.Filled.Menu, contentDescription = null
                                )
                            }
                        }
                    )
                },
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            selected = true,
                            onClick = {  },
                            icon = {
                                Icon(Icons.Filled.Favorite, contentDescription = null)
                            },
                            label = {
                                Text(text = "Favourite")
                            }
                        )
                        NavigationBarItem(
                            selected = true,
                            onClick = {  },
                            icon = {
                                Icon(Icons.Outlined.Edit, contentDescription = null)
                            },
                            label = {
                                Text(text = "Edit")
                            }
                        )
                        NavigationBarItem(
                            selected = true,
                            onClick = {  },
                            icon = {
                                Icon(Icons.Outlined.Delete, contentDescription = null)
                            },
                            label = {
                                Text(text = "Delete")
                            }
                        )
                    }
                }
            ) {
                Text(
                    modifier = Modifier.padding(it),
                    text = "Это Scaffold контент"
                )
            }
        }
    )
}

data class DrawerItem(
    val imageVector: ImageVector,
    val title: String
)