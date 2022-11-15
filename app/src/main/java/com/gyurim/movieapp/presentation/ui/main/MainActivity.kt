package com.gyurim.movieapp.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.gyurim.movieapp.R
import com.gyurim.movieapp.databinding.ActivityMainBinding
import com.gyurim.movieapp.presentation.ui.detail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private val pagingAdapter = MainPagingAdapter {
        startActivity(Intent(this, MovieDetailActivity::class.java)
            .putExtra(MovieDetailActivity.MOVIE_DETAIL_DATA, it))
    }

    val searchMovie = { query: String ->
        viewModel.searchMovieList(query)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.mainActivity = this

        binding.movieRecyclerView.apply {
            adapter = pagingAdapter
        }

        lifecycleScope.launch {
            viewModel.data.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }
}