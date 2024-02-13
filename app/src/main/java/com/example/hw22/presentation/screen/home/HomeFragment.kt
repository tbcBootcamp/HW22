package com.example.hw22.presentation.screen.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw22.databinding.FragmentHomeBinding
import com.example.hw22.presentation.base.BaseFragment
import com.example.hw22.presentation.screen.home.adapter.PostsRecyclerViewAdapter
import com.example.hw22.presentation.screen.home.adapter.StoriesRecyclerViewAdapter
import com.example.hw22.presentation.state.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val postsRecyclerAdapter = PostsRecyclerViewAdapter()
    private val storiesRecyclerAdapter = StoriesRecyclerViewAdapter()
    private val viewModel: HomeViewModel by viewModels()

    override fun setupView() {
        with(binding.recyclerViewPosts) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postsRecyclerAdapter
        }

        with(binding.recyclerViewStories) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = storiesRecyclerAdapter
        }
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(state: HomeState) {
        with(state) {
            posts?.let {
                postsRecyclerAdapter.submitList(it)
            }

            stories?.let {
                storiesRecyclerAdapter.submitList(it)
            }

        }
    }
}