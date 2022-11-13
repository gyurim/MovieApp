package com.gyurim.movieapp.presentation.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val title : String,
    val link: String,
    val image: String,
    val director: String,
    val actor: String,
    val userRating: Int
)