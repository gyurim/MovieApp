package com.gyurim.movieapp.data.repository

import com.gyurim.movieapp.data.local.datasource.MovieLocalDataSource
import com.gyurim.movieapp.data.local.entity.MovieEntity
import com.gyurim.movieapp.domain.repository.MovieBookMarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieBookMarkRepositoryImpl @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource
): MovieBookMarkRepository{

    override suspend fun getMoviesFlow(): Flow<List<MovieEntity>> {
        return movieLocalDataSource.getMoviesFlow()
    }

    override suspend fun deleteMovie(title: String) {
        movieLocalDataSource.deleteMovie(title)
    }

    override suspend fun isSavedMovie(title: String): Boolean {
        return movieLocalDataSource.isSavedMovie(title)
    }

    override suspend fun saveMovie(movie: MovieEntity) {
        movieLocalDataSource.saveMovie(movie)
    }
}