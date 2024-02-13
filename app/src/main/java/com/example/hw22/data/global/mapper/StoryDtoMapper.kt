package com.example.hw22.data.global.mapper

import com.example.hw22.data.global.model.StoryDto
import com.example.hw22.domain.model.StoryDomainModel

fun StoryDto.toDomain(): StoryDomainModel {
    return StoryDomainModel(
        id = id,
        cover = cover,
        title = title,
    )
}