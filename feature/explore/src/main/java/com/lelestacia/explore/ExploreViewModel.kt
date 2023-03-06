package com.lelestacia.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.domain.usecases.IAnimeUseCases
import com.lelestacia.model.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val animeUseCases: IAnimeUseCases
) : ViewModel() {

    val popularAnime: Flow<PagingData<Anime>> = animeUseCases.getPopularAnime()
        .cachedIn(viewModelScope)

    val airingAnime: Flow<PagingData<Anime>> = animeUseCases.getAiringAnime()
        .cachedIn(viewModelScope)

    val upcomingAnime: Flow<PagingData<Anime>> = animeUseCases.getUpcomingAnime()
        .cachedIn(viewModelScope)
}