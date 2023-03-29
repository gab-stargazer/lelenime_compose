package com.lelestacia.explore.screen.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.domain.usecases.IDashboardUseCases
import com.lelestacia.explore.component.header.HeaderScreenState
import com.lelestacia.model.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class ExplorationScreenViewModel @Inject constructor(
    private val useCases: IDashboardUseCases
) : ViewModel() {

    private val headerState: MutableStateFlow<HeaderScreenState> =
        MutableStateFlow(HeaderScreenState())

    private val searchedAnime = headerState
        .debounce(500)
        .distinctUntilChangedBy {
            it.searchQuery
        }
        .flatMapLatest {
            useCases.getAnimeSearch(searchQuery = it.searchQuery)
        }

    private val displayedStyle: MutableStateFlow<DisplayStyle> = MutableStateFlow(DisplayStyle.CARD)
    private val displayedItem: MutableStateFlow<DisplayType> = MutableStateFlow(DisplayType.POPULAR)

    private val anime: Flow<PagingData<Anime>> = displayedItem.flatMapLatest { type ->
        when (type) {
            DisplayType.POPULAR -> useCases.getPopularAnime().cachedIn(viewModelScope)
            DisplayType.AIRING -> useCases.getAiringAnime().cachedIn(viewModelScope)
            DisplayType.UPCOMING -> useCases.getUpcomingAnime().cachedIn(viewModelScope)
            DisplayType.SEARCH -> searchedAnime.cachedIn(viewModelScope)
        }
    }
    val explorationScreenState: StateFlow<ExploreScreenState> =
        combine(
            headerState,
            displayedStyle,
            displayedItem,
        ) { headerState: HeaderScreenState, displayedStyle: DisplayStyle, displayedType: DisplayType ->
            ExploreScreenState(
                headerScreenState = headerState,
                displayStyle = displayedStyle,
                displayType = displayedType,
                anime = anime
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = ExploreScreenState()
        )

    fun onEvent(event: ExploreScreenEvent) {
        when (event) {
            is ExploreScreenEvent.OnDisplayTypeChanged -> displayedItem.update {
                event.selectedType
            }

            is ExploreScreenEvent.OnDisplayStyleChanged -> displayedStyle.update {
                event.selectedStyle
            }

            ExploreScreenEvent.OnDisplayStyleOptionMenuChangedState -> headerState.update {
                it.copy(
                    isDisplayStyleOptionOpened = !it.isDisplayStyleOptionOpened
                )
            }

            is ExploreScreenEvent.OnSearchQueryChanged -> headerState.update {
                it.copy(
                    searchQuery = event.newSearchQuery
                )
            }

            ExploreScreenEvent.OnStartSearching -> headerState.update {
                it.copy(
                    isSearching = true
                )
            }
        }
    }

    fun insertOrUpdateAnimeHistory(anime: Anime) = viewModelScope.launch {
        useCases.insertOrUpdateAnimeHistory(anime = anime)
    }
}