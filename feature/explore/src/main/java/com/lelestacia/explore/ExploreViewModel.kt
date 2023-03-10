package com.lelestacia.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.domain.usecases.IDashboardUseCases
import com.lelestacia.model.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val useCases: IDashboardUseCases
) : ViewModel() {

    val popularAnime: Flow<PagingData<Anime>> = useCases.getPopularAnime()
        .cachedIn(viewModelScope)


    val airingAnime: Flow<PagingData<Anime>> = useCases.getAiringAnime()
        .cachedIn(viewModelScope)

    val upcomingAnime: Flow<PagingData<Anime>> = useCases.getUpcomingAnime()
        .cachedIn(viewModelScope)

    fun insertOrUpdateAnimeHistory(anime: Anime) = viewModelScope.launch {
        useCases.insertOrUpdateAnimeHistory(anime = anime)
    }
}