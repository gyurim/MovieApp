package com.gyurim.movieapp.data.remote

import com.gyurim.movieapp.data.remote.model.ResultSearchMovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMovieApi {
    @GET("search/movie.json")
    suspend fun searchMovieList(
        @Query("query") query: String,
        @Query("display") display: Int
    ): ResultSearchMovieList
}