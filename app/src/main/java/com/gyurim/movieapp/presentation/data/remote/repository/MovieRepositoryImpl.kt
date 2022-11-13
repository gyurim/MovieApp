package com.gyurim.movieapp.presentation.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gyurim.movieapp.presentation.data.paging.MovieSearchPagingSource
import com.gyurim.movieapp.presentation.data.remote.datasource.MovieDataSource
import com.gyurim.movieapp.presentation.data.remote.model.Movie
import com.gyurim.movieapp.presentation.domain.repository.MovieRepository
import com.gyurim.movieapp.presentation.util.Constant
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
            pagingSourceFactory = {MovieSearchPagingSource(movieDataSource, query)}
        ).flow
    }
}