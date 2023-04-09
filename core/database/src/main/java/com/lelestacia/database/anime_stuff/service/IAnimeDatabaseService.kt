package com.lelestacia.database.anime_stuff.service

import androidx.paging.PagingSource
import com.lelestacia.database.anime_stuff.entity.anime.AnimeEntity
import kotlinx.coroutines.flow.Flow

interface IAnimeDatabaseService {
    suspend fun insertOrReplaceAnime(anime: AnimeEntity)
    suspend fun getAnimeByAnimeID(animeID: Int): AnimeEntity?
    fun getAndSubscribeAnimeByAnimeID(animeID: Int): Flow<AnimeEntity>
    fun getAnimeHistory(): PagingSource<Int, AnimeEntity>
    fun getAnimeFavorite(): PagingSource<Int, AnimeEntity>
    suspend fun updateAnime(anime: AnimeEntity)
}