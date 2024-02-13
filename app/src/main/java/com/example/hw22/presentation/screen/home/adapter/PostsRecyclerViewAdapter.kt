package com.example.hw22.presentation.screen.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hw22.databinding.PostRecyclerItemBinding
import com.example.hw22.presentation.base.loadImage
import com.example.hw22.presentation.model.Image
import com.example.hw22.presentation.model.PostUiModel

class PostsRecyclerViewAdapter: ListAdapter<PostUiModel, PostsRecyclerViewAdapter.PostsViewHolder>(PostsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(PostRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind()
    }

    inner class PostsViewHolder(private val binding: PostRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind() {
            val post = currentList[adapterPosition]
            with(binding) {
                post.owner.profile?.let {
                    shapeableImageViewProfile.loadImage(it)
                }

                tvFullName.text = post.owner.firstName + " " + post.owner.lastName
                tvPostDate.text = post.owner.postDate
                tvShareContent.text = post.title

                recyclerViewImages.adapter = ImagesRecyclerViewAdapter().apply {
                    post.images?.let {
                        submitList(it.map { Image(post.id, it) })
                    }
                }
                recyclerViewImages.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)

                tvMessages.text = "${post.comments} Comments"
                tvLikes.text = "${post.likes} Likes"

            }
        }
    }

    companion object {
        private val PostsDiffCallback = object : DiffUtil.ItemCallback<PostUiModel>() {

            override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}