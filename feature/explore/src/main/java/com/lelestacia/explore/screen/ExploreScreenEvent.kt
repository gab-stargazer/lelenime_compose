package com.lelestacia.explore.screen

import com.lelestacia.common.DisplayStyle

sealed class ExploreScreenEvent {
    data class OnDisplayTypeChanged(val selectedType: DisplayType) : ExploreScreenEvent()
    object OnFilterOptionMenuChangedState : ExploreScreenEvent()
    object OnDisplayStyleOptionMenuChangedState : ExploreScreenEvent()
    data class OnDisplayStyleChanged(val selectedStyle: DisplayStyle) : ExploreScreenEvent()
    data class OnSearchQueryChanged(val newSearchQuery: String) : ExploreScreenEvent()
    object OnStartSearching : ExploreScreenEvent()
    object OnSearch : ExploreScreenEvent()
    object OnStopSearching : ExploreScreenEvent()
}