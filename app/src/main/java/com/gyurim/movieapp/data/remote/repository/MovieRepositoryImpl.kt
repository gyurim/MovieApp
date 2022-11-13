package com.gyurim.movieapp.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gyurim.movieapp.data.paging.MovieSearchPagingSource
import com.gyurim.movieapp.data.remote.NaverMovieApi
import com.gyurim.movieapp.domain.repository.MovieRepository
import com.gyurim.movieapp.data.remote.model.Movie
import com.gyurim.movieapp.util.Constant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val naverMovieApi: NaverMovieApi
) : MovieRepository {

    override suspend fun searchMovieList(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constant.INITIAL_LOAD_SIZE,
                enablePlaceholders = false
            ),
            initialKey = Constant.DEFAULT_PAGE_KEY,
            pagingSourceFactory = { MovieSearchPagingSource(naverMovieApi, query) }
        ).flow
    }
}