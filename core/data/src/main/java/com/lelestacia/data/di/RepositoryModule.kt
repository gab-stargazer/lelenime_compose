package com.lelestacia.data.di

import com.lelestacia.data.repository.AnimeRepository
import com.lelestacia.data.repository.IAnimeRepository
import com.lelestacia.database.service.IAnimeDatabaseService
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
}