package com.example.hw22.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw22.data.common.Resource
import com.example.hw22.domain.usecase.GetPostDetailsUseCase
import com.example.hw22.presentation.mapper.toPresentation
import com.example.hw22.presentation.model.PostUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPostDetailsUseCase: GetPostDetailsUseCase
) : ViewModel() {

    private val _detailsState = MutableStateFlow(DetailsState())
    val detailsState = _detailsState.asStateFlow()


     fun getDetails(id: Int) {
        viewModelScope.launch {
            getPostDetailsUseCase(id).collect {
                when (it) {
                    is Resource.Success -> {
                        _detailsState.update { currentState ->
                            currentState.copy(postDetails = it.data.toPresentation())
                        }
                    }
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                }
            }
        }
    }
}


data class DetailsState(
    val postDetails: PostUiModel? = null,
    val isLoading: Boolean = false,
)
