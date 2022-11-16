package com.gyurim.movieapp.di

import com.gyurim.movieapp.data.local.datasource.MovieLocalDataSource
import com.gyurim.movieapp.data.local.datasource.MovieLocalDataSourceImpl
import com.gyurim.movieapp.data.remote.datasource.MovieDataSource
import com.gyurim.movieapp.data.remote.datasource.MovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    @ViewModelScoped
    abstract fun bindsMovieDataSource(
        movieDataSourceImpl: MovieDataSourceImpl
    ): MovieDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindsMovieLocalDataSource(
        movieLocalDataSourceImpl: MovieLocalDataSourceImpl
    ): MovieLocalDataSource
}