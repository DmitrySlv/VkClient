package com.dscreate_app.vkclient.data.models

import com.google.gson.annotations.SerializedName

data class CommentsResponseDto(

    @SerializedName("response")
    val content: CommentsContentDto
)
