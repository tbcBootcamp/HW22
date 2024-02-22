package com.example.hw22.presentation.state

import com.example.hw22.presentation.model.PostUiModel
import com.example.hw22.presentation.model.StoryUiModel

data class HomeState (
    val isLoading: Boolean = false,
    val posts: List<PostUiModel>? = null,
    val stories: List<StoryUiModel>? = null,
    val error: String? = null
)