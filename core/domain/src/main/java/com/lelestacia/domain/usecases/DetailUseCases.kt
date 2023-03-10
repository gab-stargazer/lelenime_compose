package com.lelestacia.domain.usecases

import com.lelestacia.common.Resource
import com.lelestacia.data.repository.IAnimeRepository
import com.lelestacia.model.Anime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailUseCases @Inject constructor(
    private val animeRepository: IAnimeRepository
) : IDetailUseCases {

    override suspend fun updateAnimeFavoriteByAnimeID(animeID: Int) {
        animeRepository.updateAnimeFavoriteByAnimeID(animeID = animeID)
    }

    override fun getAnimeFromLocalDatabaseByAnimeID(animeID: Int): Flow<Resource<Anime>> {
        return animeRepository.getAnimeFromLocalDatabaseByAnimeID(animeID = animeID)
    }
}