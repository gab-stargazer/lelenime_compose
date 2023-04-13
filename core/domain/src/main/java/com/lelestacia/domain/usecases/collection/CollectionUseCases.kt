package com.lelestacia.domain.usecases.collection

import androidx.paging.PagingData
import com.lelestacia.data.repository.IAnimeRepository
import com.lelestacia.model.Anime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectionUseCases @Inject constructor(
    private val repository: IAnimeRepository
) : ICollectionUseCases {
    override suspend fun insertOrUpdateAnimeHistory(anime: Anime) {
        repository.insertOrUpdateAnimeHistory(anime = anime)
    }

    override fun getAnimeHistory(): Flow<PagingData<Anime>> {
        return repository.getAnimeHistory()
    }

    override fun getAnimeFavorite(): Flow<PagingData<Anime>> {
        return repository.getAnimeFavorite()
    }
}
