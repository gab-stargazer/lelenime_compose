package com.lelestacia.domain.usecases

import androidx.paging.PagingData
import com.lelestacia.data.repository.IAnimeRepository
import com.lelestacia.model.Anime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExploreAnimeInteractor @Inject constructor (
    private val animeRepository: IAnimeRepository
): ExploreAnimeUseCases {
    override fun getAiringAnime(): Flow<PagingData<Anime>> {
        return animeRepository.getAiringAnime()
    }
}