package com.gyurim.movieapp.domain.repository

import com.gyurim.movieapp.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieBookMarkRepository {
    suspend fun getMoviesFlow(): Flow<List<MovieEntity>>

    suspend fun saveMovie(movie: MovieEntity)

    suspend fun deleteMovie(title: String)

    suspend fun isSavedMovie(title: String): Boolean
}