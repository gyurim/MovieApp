package com.gyurim.movieapp.data.remote.model

data class MovieListResponse(
    val display: Int,
    val total: Int,
    val start: Int,
    val items : List<MovieResponse>
)
