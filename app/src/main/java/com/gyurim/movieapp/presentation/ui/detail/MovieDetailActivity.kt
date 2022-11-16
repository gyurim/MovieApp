package com.gyurim.movieapp.presentation.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gyurim.movieapp.R
import com.gyurim.movieapp.databinding.ActivityMovieDetailBinding
import com.gyurim.movieapp.domain.model.Movie

class MovieDetailActivity: AppCompatActivity() {
    private var movie : Movie = Movie("", "", "", "", "", 0.0)
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        binding.lifecycleOwner = this

        intent?.getParcelableExtra<Movie>(MOVIE_DETAIL_DATA)?.run {
            movie = this
            binding.movie = movie
        }

        val str = if (movie.isSaved) "true" else "false"
        Log.d("${movie.title} isSaved", str)

        initToolbar()
        loadWebView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadWebView() {
        binding.movieWebView.settings.apply {
            setSupportMultipleWindows(false)
            setSupportZoom(true)
            builtInZoomControls = true
            javaScriptEnabled = true
            displayZoomControls = false
        }

        binding.movieWebView.apply {
            webViewClient = WebViewClient()
            loadUrl(movie.link)
            Log.d("link", movie.link)
        }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.movieDetailToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = movie.title
        }
    }

    companion object {
        const val MOVIE_DETAIL_DATA = "MOVIE_DETAIL"
    }

}