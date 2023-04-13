package com.lelestacia.explore.stateAndEvent

import com.lelestacia.common.displayStyle.DisplayStyle
import com.lelestacia.explore.screen.DisplayType

sealed class ExploreScreenEvent {
    data class OnDisplayTypeChanged(val selectedType: DisplayType) : ExploreScreenEvent()
    object OnFilterOptionMenuChangedState : ExploreScreenEvent()
    object OnDisplayStyleOptionMenuStateChanged : ExploreScreenEvent()
    data class OnDisplayStyleChanged(val selectedStyle: DisplayStyle) : ExploreScreenEvent()
    data class OnSearchQueryChanged(val newSearchQuery: String) : ExploreScreenEvent()
    object OnStartSearching : ExploreScreenEvent()
    object OnSearch : ExploreScreenEvent()
    object OnStopSearching : ExploreScreenEvent()
}
