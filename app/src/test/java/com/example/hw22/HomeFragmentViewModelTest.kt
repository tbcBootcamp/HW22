package com.example.hw22

import com.example.hw22.data.common.Resource
import com.example.hw22.domain.model.StoryDomainModel
import com.example.hw22.domain.usecase.GetPostsUseCase
import com.example.hw22.domain.usecase.GetStoriesUseCase
import com.example.hw22.presentation.event.HomeFragmentEvents
import com.example.hw22.presentation.model.StoryUiModel
import com.example.hw22.presentation.screen.home.HomeViewModel
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class HomeFragmentViewModelTest {
    @Mock
    private lateinit var getStoriesUseCase: GetStoriesUseCase

    @Mock
    private lateinit var getPostsUseCase: GetPostsUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun before() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockitoAnnotations.openMocks(this)
        viewModel = HomeViewModel(getStoriesUseCase, getPostsUseCase)
    }

    //check if stories use case emits success
    @Test
    fun `getStories can emit success state`() = runTest {
        val storiesPres =
            listOf(StoryUiModel(1, "das", "tiutle"), StoryUiModel(2, "fdsf", "tittle"))
        val storiesDom = listOf(StoryDomainModel(1, "das", "titttle"), StoryDomainModel(2, "fdsf", "tutel"))

        whenever(getStoriesUseCase()).thenReturn(flowOf(Resource.Success(storiesDom)))

        viewModel.onEvent(HomeFragmentEvents.GetStories)

        val collectedState = viewModel.homeState.take(2).toList()

        TestCase.assertTrue("True if Success gets emmited", collectedState.any { it.stories == storiesPres })
    }

    //check if stories use case emits error
    @Test
    fun `getStories can emit error state`() = runTest {
        val error = "Some Error"
        whenever(getStoriesUseCase()).thenReturn(flowOf(Resource.Error(error)))

        viewModel.onEvent(HomeFragmentEvents.GetStories)

        val collectedState = viewModel.homeState.take(2).toList()

        TestCase.assertTrue("We need a state with error message", collectedState.any { it.error == error })
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }
}