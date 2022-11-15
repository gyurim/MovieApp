package com.gyurim.movieapp.data.local.datasource

import com.gyurim.movieapp.data.local.dao.MovieDao
import com.gyurim.movieapp.domain.model.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
): MovieLocalDataSource{
    override suspend fun deleteMovie(title: String) {
        movieDao.deleteMovie(title)
    }

    override suspend fun getMoviesFlow(): Flow<List<MovieEntity>> {
        return movieDao.getMoviesFlow()
    }

    override suspend fun isSavedMovie(title: String): Boolean {
        return movieDao.isSavedMovie(title)
    }

    override suspend fun saveMovie(movie: MovieEntity) {
        saveMovie(movie)
    }
}