package com.lelestacia.network.endpoint

import com.lelestacia.network.model.GenericPaginationResponse
import com.lelestacia.network.model.anime.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeAPI {
    @GET("seasons/now")
    suspend fun getCurrentSeason(
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>
}