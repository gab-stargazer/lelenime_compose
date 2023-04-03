package com.lelestacia.explore.state_and_event

import androidx.paging.PagingData
import com.lelestacia.common.display_style.DisplayStyle
import com.lelestacia.explore.component.header.HeaderScreenState
import com.lelestacia.explore.screen.DisplayType
import com.lelestacia.model.Anime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ExploreScreenState(
    val displayType: DisplayType = DisplayType.POPULAR,
    val displayStyle: DisplayStyle = DisplayStyle.CARD,
    val headerScreenState: HeaderScreenState = HeaderScreenState(),
    val anime: Flow<PagingData<Anime>> = flowOf(),
)
