package com.gyurim.movieapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gyurim.movieapp.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * from MovieEntity")
    fun getMovieDataPagingSource(): PagingSource<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movieEntity: MovieEntity)

    @Query("DELETE FROM MovieEntity WHERE title = :title")
    fun deleteMovie(title: String)

    @Query("SELECT EXISTS(SELECT * FROM MovieEntity WHERE title = :title)")
    fun isSavedMovie(title: String): Boolean
}