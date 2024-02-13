package com.example.hw22.data.global.mapper

import com.example.hw22.data.global.model.PostDto
import com.example.hw22.domain.model.PostDomainModel

fun PostDto.toDomain(): PostDomainModel {
    return PostDomainModel(
        id = id,
        comments = comments,
        title = title,
        images = images,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toDomain()
    )
}