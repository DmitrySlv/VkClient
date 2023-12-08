package com.dscreate_app.vkclient

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dscreate_app.vkclient.domain.FeedPost

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel) {

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
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
        val feedPosts = viewModel.feedPosts.observeAsState(listOf())

        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(
                top = 16.dp, start = 8.dp, end = 8.dp, bottom = 72.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp) // делает общий отступ между элементами в LazyColumn
        ) {
            items(
                items = feedPosts.value,
                key = { it.id }
            ) { feedPost ->
                //должна быть в теле функц LazyColumn
                val dismissState = rememberDismissState()
                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                    viewModel.remove(feedPost)
                }
                SwipeToDismissBox(
                    modifier = Modifier.animateItemPlacement(),
                    state = dismissState,
                    backgroundContent = {},
                    directions = setOf(DismissDirection.EndToStart)
                ) {
                    PostCard(
                        feedPost = feedPost,
                        onViewsClickListener = { statisticItem ->
                            viewModel.updateCount(feedPost, statisticItem)
                        },
                        onLikeClickListener = { statisticItem ->
                            viewModel.updateCount(feedPost, statisticItem)
                        },
                        onShareClickListener = { statisticItem ->
                            viewModel.updateCount(feedPost, statisticItem)
                        },
                        onCommentClickListener = { statisticItem ->
                            viewModel.updateCount(feedPost, statisticItem)
                        }
                    )
                }

            }
        }
    }
}