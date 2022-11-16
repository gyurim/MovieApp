package com.gyurim.movieapp.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gyurim.movieapp.domain.repository.MovieRepository
import com.gyurim.movieapp.domain.model.Movie
import com.gyurim.movieapp.domain.repository.MovieBookMarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
//    private val bookMarkRepository: MovieBookMarkRepository
) : ViewModel() {
    private val _movieList : MutableStateFlow<PagingData<Movie>> = MutableStateFlow(PagingData.empty())
    val movieList : StateFlow<PagingData<Movie>> = _movieList.asStateFlow()

    fun searchMovieList(query : String) {
        viewModelScope.launch {
            movieRepository.searchMovieList(query).cachedIn(viewModelScope).collectLatest {
                _movieList.value = it
            }
        }
    }

    fun checkBookmarkMovieState(movie: Movie){
        viewModelScope.launch {
//            if (bookMarkRepository.isSavedMovie(movie.title)) {
//                // 별 색칠
//            }
        }
    }

    fun changeBookmarkMovieState(movie: Movie) {
//        if (movie.isSaved) removeBookmarkMovie(movie)
//        else setBookmarkMovie(movie)
    }

//    private fun removeBookmarkMovie(movie: Movie){
//        viewModelScope.launch {
//            bookMarkRepository.deleteMovie(title = movie.title)
//        }
//    }
//
//    private fun setBookmarkMovie(movie: Movie) {
//        viewModelScope.launch {
//            bookMarkRepository.saveMovie(movie)
//        }
//    }
}