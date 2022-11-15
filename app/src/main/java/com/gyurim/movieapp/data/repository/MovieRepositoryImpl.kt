package com.gyurim.movieapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gyurim.movieapp.data.paging.MovieSearchPagingSource
import com.gyurim.movieapp.data.remote.datasource.MovieDataSource
import com.gyurim.movieapp.domain.repository.MovieRepository
import com.gyurim.movieapp.domain.model.Movie
import com.gyurim.movieapp.util.Constant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDataSource: MovieDataSource
) : MovieRepository {

    override suspend fun searchMovieList(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constant.INITIAL_LOAD_SIZE,
                enablePlaceholders = false
            ),
            initialKey = Constant.DEFAULT_PAGE_KEY,
            pagingSourceFactory = { MovieSearchPagingSource(movieDataSource, query) }
        ).flow
    }
}