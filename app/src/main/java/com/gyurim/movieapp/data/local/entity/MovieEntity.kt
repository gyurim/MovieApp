package com.gyurim.movieapp.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class MovieEntity(
    val title : String = "",
    @PrimaryKey
    val link: String = "",
    val image: String = "",
    val director: String = "",
    val actor: String = "",
    val userRating: Double = 0.0,
    val isSaved: Boolean = false
): Parcelable