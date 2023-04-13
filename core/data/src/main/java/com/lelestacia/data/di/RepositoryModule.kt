package com.lelestacia.data.di

import com.lelestacia.data.repository.AnimeRepository
import com.lelestacia.data.repository.IAnimeRepository
import com.lelestacia.data.repository.IUserPreferencesRepository
import com.lelestacia.data.repository.UserPreferencesRepository
import com.lelestacia.database.animeStuff.service.IAnimeDatabaseService
import com.lelestacia.database.user_preferences.IUserPreferencesService
import com.lelestacia.network.source.IAnimeNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAnimeRepository(
        animeNetworkService: IAnimeNetworkService,
        animeDatabaseService: IAnimeDatabaseService
    ): IAnimeRepository =
        AnimeRepository(
            animeNetworkService = animeNetworkService,
            animeDatabaseService = animeDatabaseService
        )

    @Singleton
    @Provides
    fun provideUserPreferencesRepository(
        userPreferencesService: IUserPreferencesService
    ): IUserPreferencesRepository =
        UserPreferencesRepository(
            userPreferencesService = userPreferencesService
        )
}
