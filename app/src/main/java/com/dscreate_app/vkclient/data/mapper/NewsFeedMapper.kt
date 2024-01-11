package com.dscreate_app.vkclient.data.mapper

import com.dscreate_app.vkclient.data.models.CommentsResponseDto
import com.dscreate_app.vkclient.data.models.NewsFeedResponseDto
import com.dscreate_app.vkclient.domain.entities.FeedPost
import com.dscreate_app.vkclient.domain.entities.PostComment
import com.dscreate_app.vkclient.domain.entities.StatisticItem
import com.dscreate_app.vkclient.domain.entities.StatisticType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.math.absoluteValue

class NewsFeedMapper @Inject constructor() {

    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups

        for (post in posts) {
            //.absoluteValue - делает положит, отрицательное значение.
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: break
            val feedPost = FeedPost(
                id = post.id,
                communityId = post.communityId,
                communityName = group.name,
                publicationDate = mapTimestampToDate(post.date), // *1000 для отображения верной даты
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticItem(type = StatisticType.LIKES, post.likes.count),
                    StatisticItem(type = StatisticType.COMMENTS, post.comments.count),
                    StatisticItem(type = StatisticType.VIEWS, post.views.count),
                    StatisticItem(type = StatisticType.SHARES, post.reposts.count)
                ),
                //если будет 1 то поле true,если 0 то false. Где true - лайк стоит.Из документации к API
                isLiked = post.likes.userLikes > 0
            )
            result.add(feedPost)
        }
        return result
    }

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        return SimpleDateFormat("d MMMM yyyy", Locale.getDefault()).format(date)
    }

    fun mapResponseToComments(commentsResponse: CommentsResponseDto): List<PostComment> {
        val result = mutableListOf<PostComment>()
        val listComments = commentsResponse.content.comments
        val listProfiles = commentsResponse.content.profiles
        for (comment in listComments) {
            if (comment.text.isBlank()) continue //пропускает пустой item
            val author = listProfiles.firstOrNull { it.id == comment.authorId } ?: continue //continue - если null по позиции, то перейдет к след id в списке
            val postComment = PostComment(
                id = comment.id,
                authorName = "${author.firstName} ${author.lastName}",
                authorAvatarUrl = author.avatarUrl,
                commentText = comment.text,
                publicationDate = mapTimestampToDate(comment.date)
            )
            result.add(postComment)
        }
        return result
    }
}