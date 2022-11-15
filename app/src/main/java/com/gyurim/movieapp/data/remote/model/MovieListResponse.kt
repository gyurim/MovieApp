package com.gyurim.movieapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponse(
    val display: Int,
    val total: Int,
    val start: Int,
    val items : List<MovieResponse>
)
