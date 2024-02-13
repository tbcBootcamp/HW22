package com.example.hw22.presentation.model

data class PostUiModel (
    val id: Int,
    val images: List<String>,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: OwnerUiModel
)