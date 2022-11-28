package com.gyurim.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gyurim.movieapp.data.mapper.toModel
import com.gyurim.movieapp.data.remote.datasource.MovieDataSource
import com.gyurim.movieapp.domain.model.Movie
import com.gyurim.movieapp.util.Constant

class MovieSearchPagingSource(
    private val movieDataSource: MovieDataSource,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: Constant.DEFAULT_PAGE_KEY

        return try {
            val response = movieDataSource.searchMovieList(query)
            val movieList = response.items.map { movie ->
                movie.toModel()
            }

            LoadResult.Page(
                data = movieList,
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