package com.example.hw22.presentation.screen.details

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hw22.databinding.FragmentDetailsBinding
import com.example.hw22.presentation.base.BaseFragment
import com.example.hw22.presentation.base.loadImage
import com.example.hw22.presentation.model.Image
import com.example.hw22.presentation.screen.home.adapter.ImagesRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()

    override fun setupView() {
            viewModel.getDetails(args.id)
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailsState.collect {
                    handleState(it)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleState(state: DetailsState) {
        with(binding) {
            state.postDetails?.let { post ->
                with(post) {
                    tvFullName.text = post.owner.firstName + " " + post.owner.lastName
                    tvPostDate.text = owner.postDate
                    shapeableImageViewProfile.loadImage(owner.profile)
                    tvLikes.text = "$likes likes"
                    tvMessages.text = "$comments comments"
                    recyclerViewImages.layoutManager =
                        StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
                    recyclerViewImages.adapter = ImagesRecyclerViewAdapter().apply {
                        submitList(images?.map { Image(id, it) })
                    }

                }
            }
            progressBar.isVisible = state.isLoading

//            state.errorMessage?.let {
//                binding.root.showSnackBar(resources.getString(it))
//            }
        }
    }
}