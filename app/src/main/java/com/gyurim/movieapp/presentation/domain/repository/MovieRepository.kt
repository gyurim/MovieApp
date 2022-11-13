package com.gyurim.movieapp.presentation.domain.repository

import androidx.paging.PagingData
import com.gyurim.movieapp.presentation.data.remote.model.Movie
import com.gyurim.movieapp.presentation.data.remote.model.ResultSearchMovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun searchMovieList(query: String): Flow<PagingData<Movie>>
}