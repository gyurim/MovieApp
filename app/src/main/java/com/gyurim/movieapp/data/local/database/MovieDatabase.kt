package com.gyurim.movieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gyurim.movieapp.data.local.dao.MovieDao
import com.gyurim.movieapp.domain.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}