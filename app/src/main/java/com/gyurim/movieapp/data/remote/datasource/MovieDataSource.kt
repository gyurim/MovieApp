package com.gyurim.movieapp.data.remote.datasource

import com.gyurim.movieapp.data.remote.model.ResultSearchMovieList

interface MovieDataSource {
    suspend fun searchMovieList(query: String): ResultSearchMovieList
}