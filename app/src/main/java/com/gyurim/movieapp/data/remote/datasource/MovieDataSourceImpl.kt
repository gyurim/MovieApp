package com.gyurim.movieapp.data.remote.datasource

import com.gyurim.movieapp.data.remote.NaverMovieApi
import com.gyurim.movieapp.data.remote.model.ResultSearchMovieList
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val naverMovieApi: NaverMovieApi
) : MovieDataSource {
    override suspend fun searchMovieList(query: String): ResultSearchMovieList {
        return naverMovieApi.searchMovieList(query, 100)
    }
}