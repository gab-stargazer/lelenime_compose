package com.lelestacia.domain.usecases

import androidx.paging.PagingData
import com.lelestacia.data.repository.IAnimeRepository
import com.lelestacia.model.Anime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DashboardUseCases @Inject constructor(
    private val animeRepository: IAnimeRepository
) : IDashboardUseCases {

    override suspend fun insertOrUpdateAnimeHistory(anime: Anime) {
        animeRepository.insertOrUpdateAnimeHistory(anime = anime)
    }

    override fun getAiringAnime(): Flow<PagingData<Anime>> {
        return animeRepository.getAiringAnime()
    }

    override fun getUpcomingAnime(): Flow<PagingData<Anime>> {
        return animeRepository.getUpcomingAnime()
    }

    override fun getPopularAnime(): Flow<PagingData<Anime>> {
        return animeRepository.getPopularAnime()
    }
}