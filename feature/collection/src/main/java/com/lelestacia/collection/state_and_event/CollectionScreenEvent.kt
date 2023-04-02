package com.lelestacia.collection.state_and_event

import com.lelestacia.common.display_style.DisplayStyle

sealed class CollectionScreenEvent {
    object OnDisplayStyleOptionMenuChangedState : CollectionScreenEvent()
    data class OnDisplayStyleChanged(val selectedStyle: DisplayStyle) : CollectionScreenEvent()
}
