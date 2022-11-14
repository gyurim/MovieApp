package com.gyurim.movieapp.presentation.ui

import android.widget.ImageView
import androidx.appcompat.widget.SearchView
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
            .fallback(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("setQueryTextListener")
    fun setOnQueryTextListener(view: SearchView, searchMovie: (String) -> Unit) {
        view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchMovie(it)
                }
                return false
            }
        })
    }
}