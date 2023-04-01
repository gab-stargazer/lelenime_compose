package com.lelestacia.collection.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.domain.usecases.collection.ICollectionUseCases
import com.lelestacia.model.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionScreenViewModel @Inject constructor(
    private val useCases: ICollectionUseCases
) : ViewModel() {

    private val historyAnime: Flow<PagingData<Anime>> =
        useCases.getAnimeHistory().cachedIn(viewModelScope)

    private val _collectionScreenState: MutableStateFlow<CollectionScreenState> =
        MutableStateFlow(CollectionScreenState())

    val collectionScreenState = combine(
        historyAnime, _collectionScreenState
    ) { _, state ->
        CollectionScreenState(
            displayStyle = state.displayStyle,
            isDisplayStyleOptionOpened = state.isDisplayStyleOptionOpened,
            anime = historyAnime
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CollectionScreenState()
    )

    fun onEvent(event: CollectionScreenEvent) {
        when(event) {
            is CollectionScreenEvent.OnDisplayStyleChanged -> _collectionScreenState.update {
                it.copy(
                    displayStyle = event.selectedStyle
                )
            }
            CollectionScreenEvent.OnDisplayStyleOptionMenuChangedState -> _collectionScreenState.update {
                it.copy(
                    isDisplayStyleOptionOpened = !it.isDisplayStyleOptionOpened
                )
            }
        }
    }

    fun insertOrUpdateAnimeHistory(anime: Anime) = viewModelScope.launch {
        useCases.insertOrUpdateAnimeHistory(anime = anime)
    }
}