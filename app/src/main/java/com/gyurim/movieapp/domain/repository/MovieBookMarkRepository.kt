package com.gyurim.movieapp.domain.repository

import com.gyurim.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieBookMarkRepository {
    suspend fun getMoviesFlow(): Flow<List<Movie>>

    suspend fun saveMovie(movie: Movie)

    suspend fun deleteMovie(title: String)

    suspend fun isSavedMovie(title: String): Boolean
}