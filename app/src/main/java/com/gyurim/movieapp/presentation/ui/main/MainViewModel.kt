package com.gyurim.movieapp.presentation.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.gyurim.movieapp.domain.model.Movie
import com.gyurim.movieapp.domain.repository.MovieBookMarkRepository
import com.gyurim.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val bookMarkRepository: MovieBookMarkRepository
) : ViewModel() {
    private val _movieList : MutableStateFlow<PagingData<Movie>> = MutableStateFlow(PagingData.empty())
    val movieList : StateFlow<PagingData<Movie>>
        get() = _movieList

    fun searchMovieList(query : String) {
        viewModelScope.launch {
            movieRepository.searchMovieList(query).cachedIn(viewModelScope).collect {
                it.map { movie ->
                    Log.d("${movie.isSaved}", "${movie.title}")
                    movie.isSaved = bookMarkRepository.isSavedMovie(movie.title)
                    it
                }
                _movieList.value = it
            }
        }
    }

    fun changeBookMarkState(movie: Movie): Boolean {
        return if (movie.isSaved) {
            removeBookmarkMovie(movie.title)
            false
        } else {
            setBookmarkMovie(movie)
            true
        }
    }

    private fun removeBookmarkMovie(title : String) {
        viewModelScope.launch(Dispatchers.IO) {
            bookMarkRepository.deleteMovie(title = title)
        }
    }

    private fun setBookmarkMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movie.isSaved = true
            bookMarkRepository.saveMovie(movie)
        }
    }
}