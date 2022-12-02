package com.gyurim.movieapp.data.local.datasource

import androidx.paging.PagingSource
import com.gyurim.movieapp.data.local.database.MovieDatabase
import com.gyurim.movieapp.data.local.entity.MovieEntity
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDatabase: MovieDatabase
): MovieLocalDataSource{
    private val movieDao = movieDatabase.movieDao()

    override suspend fun deleteMovie(link: String) {
        movieDao.deleteMovie(link)
    }

    override suspend fun isSavedMovie(link: String): Boolean {
        return movieDao.isSavedMovie(link)
    }

    override suspend fun saveMovie(movie: MovieEntity) {
        movieDao.saveMovie(movie)
    }

    override fun getMovieDataPagingSource(): PagingSource<Int, MovieEntity> {
        return movieDao.getMovieDataPagingSource()
    }
}