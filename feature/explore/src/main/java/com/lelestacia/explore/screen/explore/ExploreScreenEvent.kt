package com.lelestacia.explore.screen.explore

sealed class ExploreScreenEvent {
    data class OnDisplayTypeChanged(val selectedType: DisplayType) : ExploreScreenEvent()
    object OnDisplayStyleOptionMenuChangedState : ExploreScreenEvent()
    data class OnDisplayStyleChanged(val selectedStyle: DisplayStyle) : ExploreScreenEvent()
}