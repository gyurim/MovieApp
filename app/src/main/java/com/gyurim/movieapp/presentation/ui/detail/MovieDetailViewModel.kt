package com.gyurim.movieapp.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyurim.movieapp.domain.model.Movie
import com.gyurim.movieapp.domain.repository.MovieBookMarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val bookMarkRepository: MovieBookMarkRepository
) : ViewModel() {
    private val _movieFlow: MutableStateFlow<Movie> = MutableStateFlow(Movie())

    val movieFlow: StateFlow<Movie>
        get() = _movieFlow

    fun setMovieFlow(movie: Movie) {
        _movieFlow.update {
            movie
        }
    }

    fun checkBookmarkMovieState(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            if (bookMarkRepository.isSavedMovie(_movieFlow.value.link)) {
                _movieFlow.update {
                    movie.copy(isBookmarked = true)
                }
            }
        }
    }

    fun changeBookmarkMovieState() {
        if (_movieFlow.value.isBookmarked) removeBookmarkMovie()
        else setBookmarkMovie()
    }

    private fun removeBookmarkMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            bookMarkRepository.deleteMovie(link = _movieFlow.value.link)
            _movieFlow.update {
                _movieFlow.value.copy(isBookmarked = false)
            }
        }
    }

    private fun setBookmarkMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            _movieFlow.update {
                _movieFlow.value.copy(isBookmarked = true)
            }
            bookMarkRepository.saveMovie(_movieFlow.value)
        }
    }
}