package com.lelestacia.database.di

import android.content.Context
import com.lelestacia.database.anime_stuff.dao.AnimeCharacterCrossRefDao
import com.lelestacia.database.anime_stuff.dao.AnimeDao
import com.lelestacia.database.anime_stuff.dao.CharacterDao
import com.lelestacia.database.anime_stuff.dao.CharacterVoiceActorCrossRefDao
import com.lelestacia.database.anime_stuff.dao.EpisodeDao
import com.lelestacia.database.anime_stuff.dao.VoiceActorDao
import com.lelestacia.database.anime_stuff.service.AnimeDatabaseService
import com.lelestacia.database.anime_stuff.service.CharacterDatabaseService
import com.lelestacia.database.anime_stuff.service.EpisodeDatabaseService
import com.lelestacia.database.anime_stuff.service.IAnimeDatabaseService
import com.lelestacia.database.anime_stuff.service.ICharacterDatabaseService
import com.lelestacia.database.anime_stuff.service.IEpisodeDatabaseService
import com.lelestacia.database.user_preferences.IUserPreferencesService
import com.lelestacia.database.user_preferences.UserPreferencesService
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
