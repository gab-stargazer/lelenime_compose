package com.lelestacia.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.lelestacia.model.Anime
import com.lelestacia.network.model.anime.AnimeResponse

class PagingDataMapper {

    fun mapResponseToAnime(pagingData: PagingData<AnimeResponse>): PagingData<Anime> {
        return pagingData.map { it.asAnime() }
    }
}