package com.lelestacia.database.animeStuff.service

import com.lelestacia.database.animeStuff.entity.episode.EpisodeEntity

interface IEpisodeDatabaseService {
    suspend fun insertOrUpdateEpisodes(episodes: List<EpisodeEntity>)
    suspend fun getEpisodesByAnimeID(animeID: Int): List<EpisodeEntity>
}
