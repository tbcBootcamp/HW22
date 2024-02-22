package com.example.hw22.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw22.data.common.Resource
import com.example.hw22.domain.usecase.GetPostsUseCase
import com.example.hw22.domain.usecase.GetStoriesUseCase
import com.example.hw22.presentation.event.HomeFragmentEvents
import com.example.hw22.presentation.mapper.toPresentation
import com.example.hw22.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStoriesUseCase: GetStoriesUseCase,
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    fun onEvent(event: HomeFragmentEvents) {
        when (event) {
            is HomeFragmentEvents.ResetError -> setError(null)
            is HomeFragmentEvents.GetPosts -> {
                getPosts()
            }

            is HomeFragmentEvents.GetStories -> {
                getStories()
            }

        }
    }

    private fun setError(error: String?) {
        viewModelScope.launch {
            _homeState.update { currentState -> currentState.copy(error = error) }
        }
    }
    private fun getPosts() {
        viewModelScope.launch {
            getPostsUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _homeState.update { currentState -> currentState.copy(posts = it.data.map { post -> post.toPresentation() }) }
                    }
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    else -> {}
                }
            }
        }
    }

    private fun getStories() {
        viewModelScope.launch {
            getStoriesUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _homeState.update { currentState -> currentState.copy(stories = it.data.map { story -> story.toPresentation() }) }
                    }

                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    else -> {}
                }
            }
        }
    }
}