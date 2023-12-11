package com.dscreate_app.vkclient

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.dscreate_app.vkclient.domain.PostComment

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    val feedPosts = viewModel.feedPosts.observeAsState(listOf())

    if (feedPosts.value.isNotEmpty()) {
        val commentsList = mutableListOf<PostComment>().apply {
            repeat(20) {
                add(
                    PostComment(id = it)
                )
            }
        }
        CommentsScreen(feedPost = feedPosts.value[0], commentsList = commentsList)
    }
//    LazyColumn(
//        modifier = Modifier.padding(paddingValues),
//        contentPadding = PaddingValues(
//            top = 16.dp, start = 8.dp, end = 8.dp, bottom = 72.dp
//        ),
//        verticalArrangement = Arrangement.spacedBy(8.dp) // делает общий отступ между элементами в LazyColumn
//    ) {
//        items(
//            items = feedPosts.value,
//            key = { it.id }
//        ) { feedPost ->
//            //должна быть в теле функц LazyColumn
//            val dismissState = rememberDismissState()
//            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
//                viewModel.remove(feedPost)
//            }
//            SwipeToDismissBox(
//               // modifier = Modifier.animateItemPlacement(),
//                state = dismissState,
//                backgroundContent = {},
//                directions = setOf(DismissDirection.EndToStart)
//            ) {
//                PostCard(
//                    feedPost = feedPost,
//                    onViewsClickListener = { statisticItem ->
//                        viewModel.updateCount(feedPost, statisticItem)
//                    },
//                    onLikeClickListener = { statisticItem ->
//                        viewModel.updateCount(feedPost, statisticItem)
//                    },
//                    onShareClickListener = { statisticItem ->
//                        viewModel.updateCount(feedPost, statisticItem)
//                    },
//                    onCommentClickListener = { statisticItem ->
//                        viewModel.updateCount(feedPost, statisticItem)
//                    }
//                )
//            }
//
//        }
//    }
}