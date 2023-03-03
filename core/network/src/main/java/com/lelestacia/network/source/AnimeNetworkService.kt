package com.lelestacia.network.source

import androidx.paging.PagingSource
import com.lelestacia.network.endpoint.AnimeAPI
import com.lelestacia.network.model.anime.AnimeResponse
import javax.inject.Inject

class AnimeNetworkService @Inject constructor (
    private val animeAPI: AnimeAPI
): IAnimeNetworkService {
    override fun getAiringAnime(): PagingSource<Int, AnimeResponse> {
        return AiringAnimePaging(animeAPI = animeAPI)
    }
}