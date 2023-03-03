package com.lelestacia.network.source

import androidx.paging.PagingSource
import com.lelestacia.network.model.anime.AnimeResponse

interface IAnimeNetworkService {
    fun getAiringAnime(): PagingSource<Int, AnimeResponse>
}