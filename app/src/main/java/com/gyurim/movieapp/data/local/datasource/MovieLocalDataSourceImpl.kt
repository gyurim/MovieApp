package com.gyurim.movieapp.data.local.datasource

import androidx.paging.PagingSource
import com.gyurim.movieapp.data.local.dao.MovieDao
import com.gyurim.movieapp.data.local.database.MovieDatabase
import com.gyurim.movieapp.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDatabase: MovieDatabase
): MovieLocalDataSource{
    private val movieDao = movieDatabase.movieDao()

    override suspend fun deleteMovie(title: String) {
        movieDao.deleteMovie(title)
    }

//    override suspend fun getMoviesFlow(): Flow<List<MovieEntity>> {
//        return movieDao.getMoviesFlow()
//    }

    override suspend fun isSavedMovie(title: String): Boolean {
        return movieDao.isSavedMovie(title)
    }

    override suspend fun saveMovie(movie: MovieEntity) {
        saveMovie(movie)
    }

    override fun getMovieDataPagingSource(): PagingSource<Int, MovieEntity> {
        return movieDao.getMovieDataPagingSource()
    }
}