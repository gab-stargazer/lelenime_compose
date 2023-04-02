package com.lelestacia.explore.component

data class HeaderScreenState(
    val searchedAnimeTitle: String = "",
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val isFilterOptionOpened: Boolean = false,
    val isAiringStatusOptionOpened: Boolean = false,
    val isGenreOptionOpened: Boolean = false,
    val isTypeOptionOpened: Boolean = false,
    val isDisplayStyleOptionOpened: Boolean = false
)
