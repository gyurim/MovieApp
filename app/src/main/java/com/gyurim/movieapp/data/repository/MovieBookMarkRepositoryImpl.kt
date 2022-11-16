package com.gyurim.movieapp.data.repository

import com.gyurim.movieapp.data.local.datasource.MovieLocalDataSource
import com.gyurim.movieapp.data.mapper.toEntity
import com.gyurim.movieapp.data.mapper.toModel
import com.gyurim.movieapp.domain.model.Movie
import com.gyurim.movieapp.domain.repository.MovieBookMarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieBookMarkRepositoryImpl @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource
): MovieBookMarkRepository{

    override suspend fun getMoviesFlow(): Flow<List<Movie>> {
        return movieLocalDataSource.getMoviesFlow().map { entities ->
            entities.map { entity ->
                entity.toModel()
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