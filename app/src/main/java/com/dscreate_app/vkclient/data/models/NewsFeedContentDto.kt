package com.dscreate_app.vkclient.data.models

import com.google.gson.annotations.SerializedName

data class NewsFeedContentDto(
    @SerializedName("items")
    val posts: List<PostDto>,

    @SerializedName("groups")
    val groups: List<GroupDto>
)