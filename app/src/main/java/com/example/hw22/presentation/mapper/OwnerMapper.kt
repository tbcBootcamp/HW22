package com.example.hw22.presentation.mapper

import com.example.hw22.domain.model.OwnerDomainModel
import com.example.hw22.presentation.model.OwnerUiModel


fun OwnerDomainModel.toPresentation(): OwnerUiModel {
    return OwnerUiModel(
        firstName = firstName,
        lastName = lastName,
        profile = profile,
        postDate = postDate,
    )
}