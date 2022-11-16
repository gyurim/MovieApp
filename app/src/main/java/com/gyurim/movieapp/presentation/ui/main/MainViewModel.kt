package com.gyurim.movieapp.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gyurim.movieapp.domain.model.Movie
import com.gyurim.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
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
}