package com.gyurim.movieapp.presentation.di

import com.gyurim.movieapp.presentation.data.remote.repository.MovieRepositoryImpl
import com.gyurim.movieapp.presentation.domain.repository.MovieRepository
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
    abstract fun provideRepository(
        movieRepository: MovieRepositoryImpl
    ): MovieRepository
}