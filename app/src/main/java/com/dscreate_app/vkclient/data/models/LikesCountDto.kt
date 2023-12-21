package com.dscreate_app.vkclient.data.models

import com.google.gson.annotations.SerializedName

data class LikesCountDto(
    @SerializedName("likes")
    val count: Int
)
