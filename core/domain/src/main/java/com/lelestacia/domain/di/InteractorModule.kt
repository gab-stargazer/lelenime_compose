package com.lelestacia.domain.di

import com.lelestacia.data.repository.IAnimeRepository
import com.lelestacia.domain.usecases.ExploreAnimeInteractor
import com.lelestacia.domain.usecases.ExploreAnimeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InteractorModule {

    @Provides
    @ViewModelScoped
    fun provideExploreAnimeUseCases(animeRepository: IAnimeRepository) : ExploreAnimeUseCases =
        ExploreAnimeInteractor(
            animeRepository = animeRepository
        )
}