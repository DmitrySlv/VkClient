package com.dscreate_app.vkclient.data.models

import com.google.gson.annotations.SerializedName

data class NewsFeedResponseDto(
    @SerializedName("response")
    val newsFeedContent: NewsFeedContentDto
)
