package com.lelestacia.explore.component.header

data class HeaderScreenState(
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val isFilterOptionOpened: Boolean = false,
    val isDisplayStyleOptionOpened: Boolean = false
)
