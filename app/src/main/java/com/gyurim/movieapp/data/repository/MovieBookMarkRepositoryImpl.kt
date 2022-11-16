package com.gyurim.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gyurim.movieapp.data.local.datasource.MovieLocalDataSource
import com.gyurim.movieapp.data.mapper.toEntity
import com.gyurim.movieapp.data.mapper.toModel
import com.gyurim.movieapp.domain.model.Movie
import com.gyurim.movieapp.domain.repository.MovieBookMarkRepository
import com.gyurim.movieapp.util.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class MovieBookMarkRepositoryImpl @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource
): MovieBookMarkRepository{

    override fun getMoviesFlow(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constant.INITIAL_LOAD_SIZE,
                enablePlaceholders = false
            ),
            initialKey = Constant.DEFAULT_PAGE_KEY,
            pagingSourceFactory = { movieLocalDataSource.getMovieDataPagingSource() }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toModel()
            }
        }
    }

    override suspend fun deleteMovie(title: String) {
        movieLocalDataSource.deleteMovie(title)
    }

    override suspend fun isSavedMovie(title: String): Boolean {
        return movieLocalDataSource.isSavedMovie(title)
    }

    override suspend fun saveMovie(movie: Movie) {
        movieLocalDataSource.saveMovie(movie.toEntity())
    }
}