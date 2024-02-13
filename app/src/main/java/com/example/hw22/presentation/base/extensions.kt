package com.example.hw22.presentation.base

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(url: String?) {
    url?.let {
        this.visibility = View.VISIBLE
        Glide.with(context)
            .load(url)
            .into(this)
    }
}