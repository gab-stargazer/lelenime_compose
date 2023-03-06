package com.lelestacia.domain.usecases

import androidx.paging.PagingData
import com.lelestacia.data.repository.IAnimeRepository
import com.lelestacia.model.Anime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeUseCases @Inject constructor(
    private val animeRepository: IAnimeRepository
) : IAnimeUseCases {

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