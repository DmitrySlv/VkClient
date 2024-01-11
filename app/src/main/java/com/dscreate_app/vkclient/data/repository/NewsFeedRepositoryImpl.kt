package com.dscreate_app.vkclient.data.repository

import com.dscreate_app.vkclient.data.mapper.NewsFeedMapper
import com.dscreate_app.vkclient.data.network.ApiService
import com.dscreate_app.vkclient.domain.entities.AuthState
import com.dscreate_app.vkclient.domain.entities.FeedPost
import com.dscreate_app.vkclient.domain.entities.PostComment
import com.dscreate_app.vkclient.domain.entities.StatisticItem
import com.dscreate_app.vkclient.domain.entities.StatisticType
import com.dscreate_app.vkclient.domain.repository.NewsFeedRepository
import com.dscreate_app.vkclient.extantions.mergeWith
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class NewsFeedRepositoryImpl @Inject constructor (
    private val storage: VKPreferencesKeyValueStorage,
    private val apiService: ApiService,
    private val mapper: NewsFeedMapper
): NewsFeedRepository {

   private val token
       get() = VKAccessToken.restore(storage)

    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val nextDataNeededEvents = MutableSharedFlow<Unit>(replay = 1)
    private val refreshedListFlow = MutableSharedFlow<List<FeedPost>>()

    private val loadedListFlow = flow {
        nextDataNeededEvents.emit(Unit)
        nextDataNeededEvents.collect {
            val startFrom = nextFrom

            if (startFrom == null && feedPosts.isNotEmpty())  {
                emit(feedPosts)
                return@collect
            }

            val response = if (startFrom == null) {
                apiService.loadRecommendations(getAccessToken())
            } else {
                apiService.loadNextRecommendations(getAccessToken(), startFrom)
            }
            nextFrom = response.newsFeedContent.nextFrom
            val posts=  mapper.mapResponseToPosts(response)
            _feedPosts.addAll(posts)
            emit(feedPosts)
        }
    }
        .retry {
        delay(RETRY_TIMEOUT_MILLIS)
        true
    }

    private val _feedPosts = mutableListOf<FeedPost>()
    private val feedPosts: List<FeedPost>
        get() = _feedPosts.toList()

    private var nextFrom: String? = null

    private val checkAuthStateEvents = MutableSharedFlow<Unit>(replay = 1)

    private val authStateFlow = flow {
        //токен хранится в sharedPref, т.к получение/сохран токена делается быстро и других нет доступа к нему.
        // Только в приложении. Минус: можно увидеть token, если получить root доступ.
        checkAuthStateEvents.emit(Unit)
        checkAuthStateEvents.collect {
            val currentToken = token
            val loggedIn = currentToken != null && currentToken.isValid
            val authState = if (loggedIn) AuthState.Authorized else AuthState.NotAuthorized
            emit(authState)
            // Log.d("MyLog", "Token: ${token?.accessToken}") // Выдает в лог текущ токен
        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = AuthState.Initial
    )

    private val recommendations: StateFlow<List<FeedPost>> = loadedListFlow
         .mergeWith(refreshedListFlow)
         .stateIn(
             scope = coroutineScope,
             started = SharingStarted.Lazily,
             initialValue = feedPosts
         )

    private fun getAccessToken(): String {
        return token?.accessToken ?: throw IllegalStateException("Token is null")
    }

   override suspend fun loadNextData() {
        nextDataNeededEvents.emit(Unit)
    }

   override suspend fun deletePost(feedPost: FeedPost) {
        apiService.ignorePost(
            accessToken = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        _feedPosts.remove(feedPost) //после удаления с сервера удаляет локально из репозитория
        refreshedListFlow.emit(feedPosts)
    }

   override suspend fun checkAuthState() {
        checkAuthStateEvents.emit(Unit)
    }

   override suspend fun changeLikeStatus(feedPost: FeedPost) {
        val response = if (feedPost.isLiked) {
            apiService.deleteLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        } else {
            apiService.addLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
    }
        val newLikesCount = response.likesCount.count
        val newStatistics = feedPost.statistics.toMutableList().apply {
            removeIf { it.type == StatisticType.LIKES }
            add(StatisticItem(type = StatisticType.LIKES, newLikesCount))
        }
        val newPost = feedPost.copy(statistics = newStatistics, isLiked = !feedPost.isLiked)
        val postIndex = _feedPosts.indexOf(feedPost)
        _feedPosts[postIndex] = newPost
        refreshedListFlow.emit(feedPosts)
    }

    override fun getComments(feedPost: FeedPost): StateFlow<List<PostComment>> = flow {
        val comments = apiService.getComments(
            accessToken = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        emit(mapper.mapResponseToComments(comments))
    }.retry {
        delay(RETRY_TIMEOUT_MILLIS)
         true
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = listOf()
    )

    override fun getAuthStateFlow(): StateFlow<AuthState> = authStateFlow

    override fun getRecommendations(): StateFlow<List<FeedPost>> = recommendations



    companion object {
        private const val RETRY_TIMEOUT_MILLIS =  3000L
    }
}