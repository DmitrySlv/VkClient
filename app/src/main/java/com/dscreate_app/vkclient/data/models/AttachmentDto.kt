package com.dscreate_app.vkclient.data.models

import com.google.gson.annotations.SerializedName

data class AttachmentDto(
    @SerializedName("photo")
    val photo: PhotoDto?
)
