package com.gyurim.movieapp.presentation.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.gyurim.movieapp.R

object BindingAdapterObj {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setMoviePosterUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(90))
            .into(imageView)
    }
}