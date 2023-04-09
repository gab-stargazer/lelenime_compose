package com.lelestacia.database.anime_stuff.service

import com.lelestacia.database.anime_stuff.entity.episode.EpisodeEntity

interface IEpisodeDatabaseService {
    suspend fun insertOrUpdateEpisodes(episodes: List<EpisodeEntity>)
    suspend fun getEpisodesByAnimeID(animeID: Int): List<EpisodeEntity>
}