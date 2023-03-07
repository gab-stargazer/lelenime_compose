package com.lelestacia.domain.di

import com.lelestacia.data.repository.IAnimeRepository
import com.lelestacia.domain.usecases.DashboardUseCases
import com.lelestacia.domain.usecases.DetailUseCases
import com.lelestacia.domain.usecases.IDashboardUseCases
import com.lelestacia.domain.usecases.IDetailUseCases
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
    fun provideAnimeUseCases(animeRepository: IAnimeRepository): IDashboardUseCases =
        DashboardUseCases(
            animeRepository = animeRepository
        )

    @Provides
    @ViewModelScoped
    fun provideDetailAnimeUseCases(animeRepository: IAnimeRepository): IDetailUseCases =
        DetailUseCases(animeRepository = animeRepository)
}