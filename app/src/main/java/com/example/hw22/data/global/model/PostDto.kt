package com.example.hw22.data.global.model

import com.squareup.moshi.Json

data class PostDto(
    val id: Int,
    val images: List<String>?,
    val title: String,
    val comments: Int,
    val likes: Int,
    @Json(name = "share_content")
    val shareContent: String,
    val owner: OwnerDto
)

