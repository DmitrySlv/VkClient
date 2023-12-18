package com.dscreate_app.vkclient.screens

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dscreate_app.vkclient.NewsFeedScreenState
import com.dscreate_app.vkclient.PostCard
import com.dscreate_app.vkclient.domain.FeedPost
import com.dscreate_app.vkclient.view_models.NewsFeedViewModel

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onCommentClickListener: (FeedPost) -> Unit
) {
    val viewModel: NewsFeedViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.Initial)

    when (val currentState = screenState.value) {
        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                viewModel = viewModel,
                paddingValues = paddingValues,
                posts = currentState.posts,
                onCommentClickListener = onCommentClickListener
            )
        }
        NewsFeedScreenState.Initial -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedPosts(
    viewModel: NewsFeedViewModel,
    paddingValues: PaddingValues,
    posts: List<FeedPost>,
    onCommentClickListener: (FeedPost) -> Unit
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
                        onCommentClickListener(feedPost)
                    }
                )
            }

        }
    }
}