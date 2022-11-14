package com.gyurim.movieapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gyurim.movieapp.data.remote.NaverMovieApi
import com.gyurim.movieapp.data.remote.datasource.MovieDataSource
import com.gyurim.movieapp.data.remote.model.Movie
import com.gyurim.movieapp.util.Constant

class MovieSearchPagingSource(
    private val movieDataSource: MovieDataSource,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: Constant.DEFAULT_PAGE_KEY
        Log.d("load()", "")
        return try {
            val response = movieDataSource.searchMovieList(query)

            LoadResult.Page(
                data = response.items,
                prevKey = if (page == Constant.DEFAULT_PAGE_KEY) null else page - 1,
                nextKey = page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}