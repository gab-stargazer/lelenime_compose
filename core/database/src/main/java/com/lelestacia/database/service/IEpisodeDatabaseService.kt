package com.lelestacia.database.service

import com.lelestacia.database.entity.episode.EpisodeEntity

interface IEpisodeDatabaseService {
    suspend fun insertOrUpdateEpisodes(episodes: List<EpisodeEntity>)
    suspend fun getEpisodesByAnimeID(animeID: Int): List<EpisodeEntity>
}