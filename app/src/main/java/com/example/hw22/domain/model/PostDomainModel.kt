package com.example.hw22.domain.model

data class PostDomainModel (
    val id: Int,
    val images: List<String>,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: OwnerDomainModel
)