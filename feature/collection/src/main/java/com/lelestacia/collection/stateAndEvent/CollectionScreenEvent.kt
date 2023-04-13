package com.lelestacia.collection.stateAndEvent

import com.lelestacia.common.displayStyle.DisplayStyle

sealed class CollectionScreenEvent {
    object OnDisplayStyleOptionMenuChangedState : CollectionScreenEvent()
    data class OnDisplayStyleChanged(val selectedStyle: DisplayStyle) : CollectionScreenEvent()
}
