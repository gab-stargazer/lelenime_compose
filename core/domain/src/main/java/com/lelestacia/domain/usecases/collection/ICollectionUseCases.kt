package com.lelestacia.domain.usecases.collection

import androidx.paging.PagingData
import com.lelestacia.model.Anime
import kotlinx.coroutines.flow.Flow

interface ICollectionUseCases {
    suspend fun insertOrUpdateAnimeHistory(anime: Anime)
    fun getAnimeHistory(): Flow<PagingData<Anime>>
    fun getAnimeFavorite(): Flow<PagingData<Anime>>
}