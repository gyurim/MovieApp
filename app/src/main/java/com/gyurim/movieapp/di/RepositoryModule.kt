package com.gyurim.movieapp.di

import com.gyurim.movieapp.data.repository.MovieBookMarkRepositoryImpl
import com.gyurim.movieapp.data.repository.MovieRepositoryImpl
import com.gyurim.movieapp.domain.repository.MovieBookMarkRepository
import com.gyurim.movieapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindsMovieRepository(
        movieRepository: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsMovieBookMarkRepository(
        movieBookMarkRepositoryImpl: MovieBookMarkRepositoryImpl
    ): MovieBookMarkRepository
}