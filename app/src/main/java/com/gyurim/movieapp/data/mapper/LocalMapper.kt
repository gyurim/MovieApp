package com.gyurim.movieapp.data.mapper


import com.gyurim.movieapp.data.local.entity.MovieEntity
import com.gyurim.movieapp.domain.model.Movie

fun MovieEntity.toModel() = Movie(
    title = title,
    link = link,
    image = image,
    director = director,
    actor = actor,
    userRating = userRating,
    isSaved = isSaved
)

fun Movie.toEntity() = MovieEntity(
    title = title,
    link = link,
    image = image,
    director = director,
    actor = actor,
    userRating = userRating,
    isSaved = isSaved
)