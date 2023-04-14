package com.lelestacia.database.di

import android.content.Context
import com.lelestacia.database.animeStuff.dao.AnimeCharacterCrossRefDao
import com.lelestacia.database.animeStuff.dao.AnimeDao
import com.lelestacia.database.animeStuff.dao.CharacterDao
import com.lelestacia.database.animeStuff.dao.CharacterVoiceActorCrossRefDao
import com.lelestacia.database.animeStuff.dao.EpisodeDao
import com.lelestacia.database.animeStuff.dao.VoiceActorDao
import com.lelestacia.database.animeStuff.service.AnimeDatabaseService
import com.lelestacia.database.animeStuff.service.CharacterDatabaseService
import com.lelestacia.database.animeStuff.service.EpisodeDatabaseService
import com.lelestacia.database.animeStuff.service.IAnimeDatabaseService
import com.lelestacia.database.animeStuff.service.ICharacterDatabaseService
import com.lelestacia.database.animeStuff.service.IEpisodeDatabaseService
import com.lelestacia.database.userPreferences.IUserPreferencesService
import com.lelestacia.database.userPreferences.UserPreferencesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideUserPreferencesService(
        @ApplicationContext context: Context
    ): IUserPreferencesService =
        UserPreferencesService(context = context)
}
