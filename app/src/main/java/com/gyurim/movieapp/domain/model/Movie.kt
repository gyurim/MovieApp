package com.gyurim.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val title : String = "",
    val link: String = "", // unique key
    val image: String = "",
    val director: String = "",
    val actor: String = "",
    val userRating: Double = 0.0,
    var isBookmarked: Boolean = false
): Parcelable