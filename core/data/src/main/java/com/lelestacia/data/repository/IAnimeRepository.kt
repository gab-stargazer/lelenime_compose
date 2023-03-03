package com.lelestacia.data.repository

import androidx.paging.PagingData
import com.lelestacia.model.Anime
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {
    fun getAiringAnime(): Flow<PagingData<Anime>>
}