package com.gyurim.movieapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ResultSearchMovieList(
    val display: Int,
    val total: Int,
    val start: Int,
    val items : List<Movie>
)
