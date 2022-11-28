package com.gyurim.movieapp.presentation.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.gyurim.movieapp.domain.model.Movie
import com.gyurim.movieapp.domain.repository.MovieBookMarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieBookmarkViewModel @Inject constructor(
    private val bookMarkRepository: MovieBookMarkRepository
) : ViewModel(){
    private val _movieListFlow : MutableStateFlow<PagingData<Movie>> = MutableStateFlow(PagingData.empty())
    val movieListFlow : StateFlow<PagingData<Movie>>
        get() = _movieListFlow

    init {
        getMoviesFlow()
    }

    private fun getMoviesFlow(){
        viewModelScope.launch {
            bookMarkRepository.getMoviesFlow().cachedIn(viewModelScope).collectLatest {
                it.map { movie ->
                    movie.isSaved = bookMarkRepository.isSavedMovie(movie.title)
                }
                _movieListFlow.value = it
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