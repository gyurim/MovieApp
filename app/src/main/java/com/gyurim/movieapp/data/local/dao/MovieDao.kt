package com.gyurim.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gyurim.movieapp.domain.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * from MovieEntity")
    fun getMoviesFlow(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movieEntity: MovieEntity)

    @Query("DELETE FROM MovieEntity WHERE title = :title")
    fun deleteMovie(title: String)

    @Query("SELECT EXISTS(SELECT * FROM MOVIEENTITY title = :title)")
    fun isSavedMovie(title: String): Boolean
}