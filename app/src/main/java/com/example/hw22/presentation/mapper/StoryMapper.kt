package com.example.hw22.presentation.mapper

import com.example.hw22.domain.model.StoryDomainModel
import com.example.hw22.presentation.model.StoryUiModel

fun StoryDomainModel.toPresentation(): StoryUiModel {
    return StoryUiModel(
        id = id,
        cover = cover,
        title = title,
    )
}