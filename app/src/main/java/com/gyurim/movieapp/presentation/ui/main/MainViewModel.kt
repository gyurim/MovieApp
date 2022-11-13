package com.gyurim.movieapp.presentation.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gyurim.movieapp.presentation.data.remote.model.Movie
import com.gyurim.movieapp.presentation.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: MovieRepository
) : ViewModel() {
    private val _data : MutableStateFlow<PagingData<Movie>> = MutableStateFlow(PagingData.empty())
    val data : StateFlow<PagingData<Movie>> = _data.asStateFlow()

    init {
        viewModelScope.launch {
            repository.searchMovieList("국가").cachedIn(viewModelScope)
                .collectLatest { list ->
                    _data.emit(list)
                }
        }
    }
}