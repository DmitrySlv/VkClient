package com.dscreate_app.vkclient.data.models

import com.google.gson.annotations.SerializedName

data class LikesDto(
    @SerializedName("count")
    val count: Int,

    @SerializedName("user_likes")
    val userLikes: Int
)
