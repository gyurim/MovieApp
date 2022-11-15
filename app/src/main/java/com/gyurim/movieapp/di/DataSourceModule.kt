package com.gyurim.movieapp.di

import com.gyurim.movieapp.data.local.datasource.MovieLocalDataSource
import com.gyurim.movieapp.data.local.datasource.MovieLocalDataSourceImpl
import com.gyurim.movieapp.data.remote.datasource.MovieDataSource
import com.gyurim.movieapp.data.remote.datasource.MovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsMovieDataSource(
        movieDataSourceImpl: MovieDataSourceImpl
    ): MovieDataSource

    @Binds
    @Singleton
    abstract fun bindsMovieLocalDataSource(
        movieLocalDataSourceImpl: MovieLocalDataSourceImpl
    ): MovieLocalDataSource
}