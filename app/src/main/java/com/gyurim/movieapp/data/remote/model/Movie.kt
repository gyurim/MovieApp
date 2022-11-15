package com.gyurim.movieapp.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title : String,
    val link: String,
    val image: String,
    val director: String,
    val actor: String,
    val userRating: Double
): Parcelable