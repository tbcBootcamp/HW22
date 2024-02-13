package com.example.hw22.presentation.mapper

import com.example.hw22.domain.model.PostDomainModel
import com.example.hw22.presentation.model.PostUiModel



fun PostDomainModel.toPresentation(): PostUiModel {
    return PostUiModel(
        id = id,
        images = images,
        title = title,
        comments = comments,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toPresentation()
    )
}