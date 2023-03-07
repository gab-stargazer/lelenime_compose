package com.lelestacia.database.di

import com.lelestacia.database.dao.AnimeCharacterCrossRefDao
import com.lelestacia.database.dao.AnimeDao
import com.lelestacia.database.dao.CharacterDao
import com.lelestacia.database.dao.CharacterVoiceActorCrossRefDao
import com.lelestacia.database.dao.EpisodeDao
import com.lelestacia.database.dao.VoiceActorDao
import com.lelestacia.database.service.AnimeDatabaseService
import com.lelestacia.database.service.CharacterDatabaseService
import com.lelestacia.database.service.EpisodeDatabaseService
import com.lelestacia.database.service.IAnimeDatabaseService
import com.lelestacia.database.service.ICharacterDatabaseService
import com.lelestacia.database.service.IEpisodeDatabaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideCharacterDatabaseService(
        animeCharacterCrossRefDao: AnimeCharacterCrossRefDao,
        characterVoiceActorCrossRefDao: CharacterVoiceActorCrossRefDao,
        characterDao: CharacterDao,
        voiceActorDao: VoiceActorDao
    ): ICharacterDatabaseService =
        CharacterDatabaseService(
            animeCharactersCrossRefDao = animeCharacterCrossRefDao,
            characterVoiceActorsCrossRefDao = characterVoiceActorCrossRefDao,
            characterDao = characterDao,
            voiceActorDao = voiceActorDao
        )

    @Singleton
    @Provides
    fun provideEpisodeDatabaseService(
        episodeDao: EpisodeDao
    ): IEpisodeDatabaseService =
        EpisodeDatabaseService(
            episodeDao = episodeDao
        )

    @Singleton
    @Provides
    fun provideAnimeDatabaseService(
        animeDao: AnimeDao
    ): IAnimeDatabaseService =
        AnimeDatabaseService(
            animeDao = animeDao
        )
}
