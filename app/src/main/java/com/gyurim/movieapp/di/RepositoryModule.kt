package com.gyurim.movieapp.di

import com.gyurim.movieapp.data.repository.MovieBookMarkRepositoryImpl
import com.gyurim.movieapp.data.repository.MovieRepositoryImpl
import com.gyurim.movieapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsMovieRepository(
        movieRepository: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    abstract fun bindsMovieBookMarkRepository(
        movieBookMarkRepositoryImpl: MovieBookMarkRepositoryImpl
    ): MovieRepository
}