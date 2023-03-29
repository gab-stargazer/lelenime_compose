package com.lelestacia.explore.screen.explore

sealed class ExploreScreenEvent {
    data class OnDisplayTypeChanged(val selectedType: DisplayType) : ExploreScreenEvent()
    object OnDisplayStyleOptionMenuChangedState : ExploreScreenEvent()
    data class OnDisplayStyleChanged(val selectedStyle: DisplayStyle) : ExploreScreenEvent()
    data class OnSearchQueryChanged(val newSearchQuery: String) : ExploreScreenEvent()
    object OnStartSearching: ExploreScreenEvent()
    object OnStopSearching: ExploreScreenEvent()
}