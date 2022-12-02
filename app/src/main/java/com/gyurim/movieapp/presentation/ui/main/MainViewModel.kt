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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
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
        // TODO: withContext를 사용해서 순차 적용
        viewModelScope.launch {
            movieRepository.searchMovieList(query).cachedIn(viewModelScope).collectLatest {
                // PagingData.map은 lazy transformation
                // .submitData(pagingData)를 호출할 때 collection이 진행되는 lazy transformation
                // 그래서 untransformated PagingData를 submit한다면, .map은 절대 작동되지 않을 것

                _movieList.value = it

                it.map { movie ->
                    Log.d("${movie.isBookmarked}", "${movie.title}")
                    movie.isBookmarked = bookMarkRepository.isSavedMovie(movie.title)
                    it
                }
            }
        }
    }

    fun changeBookMarkState(movie: Movie): Boolean {
        return if (movie.isBookmarked) {
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
            movie.isBookmarked = true
            bookMarkRepository.saveMovie(movie)
        }
    }
}