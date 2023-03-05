package com.lelestacia.domain.di

import com.lelestacia.data.repository.IAnimeRepository
import com.lelestacia.domain.usecases.AnimeUseCases
import com.lelestacia.domain.usecases.IAnimeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideAnimeUseCases(animeRepository: IAnimeRepository): IAnimeUseCases =
        AnimeUseCases(
            animeRepository = animeRepository
        )
}