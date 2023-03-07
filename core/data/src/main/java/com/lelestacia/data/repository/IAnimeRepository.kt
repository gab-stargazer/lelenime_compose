package com.lelestacia.data.repository

import androidx.paging.PagingData
import com.lelestacia.model.Anime
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {
    suspend fun insertOrUpdateAnimeHistory(anime: Anime)
    suspend fun updateAnimeFavoriteByAnimeID(animeID: Int)
    fun getAiringAnime(): Flow<PagingData<Anime>>
    fun getUpcomingAnime(): Flow<PagingData<Anime>>
    fun getPopularAnime(): Flow<PagingData<Anime>>
    fun getAnimeFromLocalDatabaseByAnimeID(animeID: Int): Flow<Anime>
}