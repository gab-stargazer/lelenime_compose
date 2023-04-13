package com.lelestacia.collection.stateAndEvent

import androidx.paging.PagingData
import com.lelestacia.common.displayStyle.DisplayStyle
import com.lelestacia.model.Anime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class CollectionScreenState(
    val displayStyle: DisplayStyle = DisplayStyle.CARD,
    val isDisplayStyleOptionOpened: Boolean = false,
    val anime: Flow<PagingData<Anime>> = flowOf()
)
