package com.gyurim.movieapp.domain.repository

import androidx.paging.PagingData
import com.gyurim.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieBookMarkRepository {
    fun getMoviesFlow(): Flow<PagingData<Movie>>

    suspend fun saveMovie(movie: Movie)

    suspend fun deleteMovie(title: String)

    suspend fun isSavedMovie(title: String): Boolean
}