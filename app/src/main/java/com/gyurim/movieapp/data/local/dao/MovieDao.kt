package com.gyurim.movieapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gyurim.movieapp.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * from MovieEntity")
    fun getMovieDataPagingSource(): PagingSource<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movieEntity: MovieEntity)

    @Query("DELETE FROM MovieEntity WHERE link = :link")
    fun deleteMovie(link: String)

    @Query("SELECT EXISTS(SELECT * FROM MovieEntity WHERE link = :link)")
    fun isSavedMovie(link: String): Boolean
}