package com.gyurim.movieapp.presentation.data.remote

import com.gyurim.movieapp.presentation.data.remote.model.ResultSearchMovieList
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverMovieApi {
    @GET("search/movie.json")
    suspend fun searchMovieList(
        @Query("query") query: String
    ): ResultSearchMovieList
}