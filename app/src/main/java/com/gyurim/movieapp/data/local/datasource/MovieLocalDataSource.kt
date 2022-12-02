package com.gyurim.movieapp.data.local.datasource

import androidx.paging.PagingSource
import com.gyurim.movieapp.data.local.entity.MovieEntity

interface MovieLocalDataSource {
    fun getMovieDataPagingSource(): PagingSource<Int, MovieEntity>

    suspend fun deleteMovie(link: String)

    suspend fun isSavedMovie(link: String): Boolean

    suspend fun saveMovie(movie: MovieEntity)
}