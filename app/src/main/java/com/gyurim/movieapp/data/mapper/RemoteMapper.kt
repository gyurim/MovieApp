package com.gyurim.movieapp.data.mapper

import com.gyurim.movieapp.data.remote.model.MovieResponse
import com.gyurim.movieapp.domain.model.Movie

fun MovieResponse.toModel(): Movie {
    return Movie(
        title = this.title,
        link = this.link,
        image = this.image,
        director = this.director,
        actor = this.actor,
        userRating = this.userRating,
        isBookmarked = false
    )
}