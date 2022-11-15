package com.gyurim.movieapp.data.remote.datasource

import com.gyurim.movieapp.data.remote.model.MovieListResponse

interface MovieDataSource {
    suspend fun searchMovieList(query: String): MovieListResponse
}