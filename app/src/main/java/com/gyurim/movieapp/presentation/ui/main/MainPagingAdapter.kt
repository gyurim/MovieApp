package com.gyurim.movieapp.presentation.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyurim.movieapp.databinding.ItemMovieBinding
import com.gyurim.movieapp.domain.model.Movie

class MainPagingAdapter(
    private val itemClick: (Movie) -> Unit,
    private val itemBookmarkClick: (Movie) -> Boolean
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
        fun bind(item: Movie, itemClick: (Movie) -> Unit, itemBookMarkClick: (Movie) -> Boolean) {
            with(binding) {
                movie = item
                movieContainer.setOnClickListener {
                    itemClick.invoke(item)
                }
                movieBookmarkButton.setOnClickListener {
                    // itemBookMarkClick.invoke(item)의 리턴값
                    // true -> item이 bookmark에 추가된 경우
                    // false -> item이 bookmark에서 제거된 경우
                    it.isSelected = itemBookMarkClick.invoke(item)
                    item.isSaved = it.isSelected
                    Log.d("${item.title}", "${it.isSelected}")
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