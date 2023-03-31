package com.lelestacia.collection.screen

import com.lelestacia.common.DisplayStyle

sealed class CollectionScreenEvent {
    object OnDisplayStyleOptionMenuChangedState : CollectionScreenEvent()
    data class OnDisplayStyleChanged(val selectedStyle: DisplayStyle) : CollectionScreenEvent()
}
