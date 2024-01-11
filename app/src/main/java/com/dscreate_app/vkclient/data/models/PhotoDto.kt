package com.dscreate_app.vkclient.data.models

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("sizes")
    val photoUrls: List<PhotoUrlDto>
)
