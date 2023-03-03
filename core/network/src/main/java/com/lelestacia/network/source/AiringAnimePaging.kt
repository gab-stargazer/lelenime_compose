package com.lelestacia.network.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lelestacia.network.endpoint.AnimeAPI
import com.lelestacia.network.model.anime.AnimeResponse
import timber.log.Timber

class AiringAnimePaging(
    private val animeAPI: AnimeAPI
) : PagingSource<Int, AnimeResponse>() {
    override fun getRefreshKey(state: PagingState<Int, AnimeResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeResponse> {
        return try {
            val currentPage = params.key ?: 1
            val apiResponse = animeAPI.getCurrentSeason(page = currentPage)
            val hasNextPage = apiResponse.pagination.hasNextPage
            LoadResult.Page(
                data = apiResponse.data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (hasNextPage) currentPage + 1 else null
            )
        } catch (e: Exception) {
            Timber.e(e)
            LoadResult.Error(e)
        }
    }
}