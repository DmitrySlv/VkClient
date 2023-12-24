package com.dscreate_app.vkclient.presentation.screens.news.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dscreate_app.vkclient.data.repository.NewsFeedRepositoryImpl
import com.dscreate_app.vkclient.domain.FeedPost
import com.dscreate_app.vkclient.domain.StatisticItem
import com.dscreate_app.vkclient.presentation.screens.news.NewsFeedScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsFeedViewModel(application: Application): AndroidViewModel(application) {

    private val initialState = NewsFeedScreenState.Initial

    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    private val repository = NewsFeedRepositoryImpl(application)

    init {
        _screenState.value = NewsFeedScreenState.Loading
        loadRecommendations()
    }

    private fun loadRecommendations() {
        viewModelScope.launch {
            val feedPosts = repository.loadRecommendations()
            _screenState.value = NewsFeedScreenState.Posts(posts = feedPosts)
        }
    }

    fun loadNextRecommendations() {
        _screenState.value = NewsFeedScreenState.Posts(
            posts = repository.feedPosts,
            nextDataIsLoading = true
        )
        loadRecommendations()
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.changeLikeStatus(feedPost)
            _screenState.value = NewsFeedScreenState.Posts(posts = repository.feedPosts)
        }
    }

    fun remove(feedPost: FeedPost) {
       viewModelScope.launch {
           repository.deletePost(feedPost)
           _screenState.value = NewsFeedScreenState.Posts(posts = repository.feedPosts)
       }
    }
}