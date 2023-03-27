package com.lelestacia.explore.screen.explore

import androidx.paging.PagingData
import com.lelestacia.model.Anime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ExploreScreenState(
    val displayType: DisplayType = DisplayType.POPULAR,
    val anime: Flow<PagingData<Anime>> = flowOf()
)
