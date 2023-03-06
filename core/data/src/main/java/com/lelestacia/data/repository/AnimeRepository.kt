package com.lelestacia.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lelestacia.data.mapper.PagingDataMapper
import com.lelestacia.model.Anime
import com.lelestacia.network.model.anime.AnimeResponse
import com.lelestacia.network.source.IAnimeNetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimeRepository @Inject constructor(
    private val animeNetworkService: IAnimeNetworkService,
    private val mapper: PagingDataMapper = PagingDataMapper()
) : IAnimeRepository {

    override fun getAiringAnime(): Flow<PagingData<Anime>> {
        return getAnimePager(1).flow.map { pagingData ->
            mapper.mapResponseToAnime(pagingData = pagingData)
        }
    }

    override fun getUpcomingAnime(): Flow<PagingData<Anime>> {
        return getAnimePager(2).flow.map { pagingData ->
            mapper.mapResponseToAnime(pagingData = pagingData)
        }
    }

    override fun getPopularAnime(): Flow<PagingData<Anime>> {
        return getAnimePager(3).flow.map { pagingData ->
            mapper.mapResponseToAnime(pagingData = pagingData)
        }
    }

    private fun getAnimePager(number: Int): Pager<Int, AnimeResponse> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 5,
                enablePlaceholders = false,
                initialLoadSize = 25
            ),
            pagingSourceFactory = {
                when (number) {
                    1 -> animeNetworkService.getAiringAnime()
                    2 -> animeNetworkService.getUpcomingAnime()
                    else -> animeNetworkService.getPopularAnime()
                }
            }
        )
    }
}