package com.gyurim.movieapp.data.local.datasource

import com.gyurim.movieapp.domain.model.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    suspend fun getMoviesFlow(): Flow<List<MovieEntity>>

    suspend fun deleteMovie(title: String)

    suspend fun isSavedMovie(title: String): Boolean

    suspend fun saveMovie(movie: MovieEntity)
}