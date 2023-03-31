package com.lelestacia.domain.usecases.detail

import com.lelestacia.common.Resource
import com.lelestacia.model.Anime
import kotlinx.coroutines.flow.Flow

interface IDetailUseCases {
    suspend fun updateAnimeFavoriteByAnimeID(animeID: Int)
    fun getAnimeFromLocalDatabaseByAnimeID(animeID: Int) : Flow<Resource<Anime>>
}