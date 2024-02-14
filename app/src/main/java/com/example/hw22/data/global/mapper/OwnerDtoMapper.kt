package com.example.hw22.data.global.mapper

import com.example.hw22.data.global.model.OwnerDto
import com.example.hw22.domain.model.OwnerDomainModel

fun OwnerDto.toDomain(): OwnerDomainModel {
    return OwnerDomainModel(
        firstName = firstName,
        lastName = lastName,
        profile = profile ?: "",
        postDate = postDate,
    )
}