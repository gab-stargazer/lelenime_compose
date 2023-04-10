package com.lelestacia.domain.di

import com.lelestacia.data.repository.IAnimeRepository
import com.lelestacia.data.repository.IUserPreferencesRepository
import com.lelestacia.domain.usecases.collection.CollectionUseCases
import com.lelestacia.domain.usecases.collection.ICollectionUseCases
import com.lelestacia.domain.usecases.detail.DetailUseCases
import com.lelestacia.domain.usecases.detail.IDetailUseCases
import com.lelestacia.domain.usecases.explore.ExploreUseCases
import com.lelestacia.domain.usecases.explore.IExploreUseCases
import com.lelestacia.domain.usecases.settings.IUserPreferencesUseCases
import com.lelestacia.domain.usecases.settings.UserPreferencesUseCases
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
    fun provideExploreAnimeUseCases(animeRepository: IAnimeRepository): IExploreUseCases =
        ExploreUseCases(
            repository = animeRepository
        )

    @Provides
    @ViewModelScoped
    fun provideCollectionAnimeUseCases(animeRepository: IAnimeRepository): ICollectionUseCases =
        CollectionUseCases(
            repository = animeRepository
        )

    @Provides
    @ViewModelScoped
    fun provideDetailAnimeUseCases(animeRepository: IAnimeRepository): IDetailUseCases =
        DetailUseCases(
            repository = animeRepository
        )

    @Provides
    @ViewModelScoped
    fun provideUserPreferencesUseCases(
        userPreferencesRepository: IUserPreferencesRepository
    ): IUserPreferencesUseCases =
        UserPreferencesUseCases(
            userPreferencesRepository = userPreferencesRepository
        )
}