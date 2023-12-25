package com.dscreate_app.vkclient.domain

data class PostComment(
    val id: Long,
    val authorName: String,
    val authorAvatarUrl: String,
    val commentText: String,
    val publicationDate: String
)
