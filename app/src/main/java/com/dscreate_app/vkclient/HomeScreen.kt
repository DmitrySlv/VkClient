package com.dscreate_app.vkclient

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dscreate_app.vkclient.domain.FeedPost

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    val screenState= viewModel.screenState.observeAsState(HomeScreenState.Initial)

    when (val currentState = screenState.value) {
        is HomeScreenState.Posts -> {
            FeedPosts(
                viewModel = viewModel,
                paddingValues = paddingValues,
                posts = currentState.posts
            )
        }
        is HomeScreenState.Comments -> {
            CommentsScreen(
                feedPost = currentState.feedPost,
                commentsList = currentState.comments,
                onBackPressed = {
                    viewModel.closeComments()
                }
            )
            BackHandler { //При клике на кнопку назад на устройстве, возвращает назад на экран, а не закрывает приложение.
                viewModel.closeComments()
            }
        }
        HomeScreenState.Initial -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedPosts(
    viewModel: MainViewModel,
    paddingValues: PaddingValues,
    posts: List<FeedPost>,
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(
            top = 16.dp, start = 8.dp, end = 8.dp, bottom = 72.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp) // делает общий отступ между элементами в LazyColumn
    ) {
        items(
            items = posts,
            key = { it.id }
        ) { feedPost ->
            //должна быть в теле функц LazyColumn
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.remove(feedPost)
            }
            SwipeToDismissBox(
                // modifier = Modifier.animateItemPlacement(),
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
                    onCommentClickListener = {
                        viewModel.showComments(feedPost)
                    }
                )
            }

        }
    }
}