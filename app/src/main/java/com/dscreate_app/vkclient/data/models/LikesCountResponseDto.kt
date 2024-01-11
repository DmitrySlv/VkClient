package com.dscreate_app.vkclient.data.models

import com.google.gson.annotations.SerializedName

data class LikesCountResponseDto(
    @SerializedName("response")
    val likesCount: LikesCountDto
)
