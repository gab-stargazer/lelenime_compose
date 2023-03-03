package com.lelestacia.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.lelestacia.data.mapper.asAnime
import com.lelestacia.model.Anime
import com.lelestacia.network.source.IAnimeNetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimeRepository @Inject constructor(
    private val animeNetworkService: IAnimeNetworkService
) : IAnimeRepository {
    override fun getAiringAnime(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                initialLoadSize = 25,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                animeNetworkService.getAiringAnime()
            }
        ).flow.map { pagingData ->
            pagingData.map { it.asAnime() }
        }
    }
}