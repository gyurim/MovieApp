package com.gyurim.movieapp.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyurim.movieapp.databinding.ItemMovieBinding
import com.gyurim.movieapp.domain.model.Movie

class MainPagingAdapter(
    private val itemClick: (Movie) -> Unit,
    private val itemBookmarkClick: (Movie) -> Unit
) : PagingDataAdapter<Movie, MainPagingAdapter.MainViewHolder>(diffUtil){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, itemClick, itemBookmarkClick)
        }
    }

    inner class MainViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie, itemClick: (Movie) -> Unit, itemBookMarkClick: (Movie) -> Unit) {
            with(binding) {
                movie = item
                movieContainer.setOnClickListener {
                    itemClick.invoke(item)
                }
                movieBookmarkButton.setOnClickListener {
                    itemBookMarkClick.invoke(item)
                }
                executePendingBindings()
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}