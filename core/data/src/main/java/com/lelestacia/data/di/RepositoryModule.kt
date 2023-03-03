package com.lelestacia.data.di

import com.lelestacia.data.repository.AnimeRepository
import com.lelestacia.data.repository.IAnimeRepository
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
    fun provideAnimeRepository(networkService: IAnimeNetworkService): IAnimeRepository =
        AnimeRepository(
            animeNetworkService = networkService
        )
}