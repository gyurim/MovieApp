package com.gyurim.movieapp.presentation.data.remote.datasource

import com.gyurim.movieapp.presentation.data.remote.model.ResultSearchMovieList

interface MovieDataSource {
    suspend fun searchMovieList(query: String): ResultSearchMovieList
}