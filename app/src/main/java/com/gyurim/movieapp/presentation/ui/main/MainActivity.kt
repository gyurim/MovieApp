package com.gyurim.movieapp.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.gyurim.movieapp.R
import com.gyurim.movieapp.databinding.ActivityMainBinding
import com.gyurim.movieapp.presentation.data.remote.NaverMovieApi
import com.gyurim.movieapp.presentation.data.remote.model.ResultSearchMovieList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val pagingAdapter = MainPagingAdapter {
//        binding.root.context.startActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.movieRecyclerView.apply {
            adapter = pagingAdapter
        }

    }
}