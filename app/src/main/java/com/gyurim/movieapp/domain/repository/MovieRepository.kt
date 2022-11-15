package com.gyurim.movieapp.domain.repository

import androidx.paging.PagingData
import com.gyurim.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun searchMovieList(query: String): Flow<PagingData<Movie>>
}