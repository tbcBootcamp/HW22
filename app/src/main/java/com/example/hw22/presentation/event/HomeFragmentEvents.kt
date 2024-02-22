package com.example.hw22.presentation.event

sealed class HomeFragmentEvents {
    data object ResetError : HomeFragmentEvents()
    data object GetPosts : HomeFragmentEvents()
    data object GetStories : HomeFragmentEvents()
}
