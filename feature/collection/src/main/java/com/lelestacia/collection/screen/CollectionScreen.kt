package com.lelestacia.collection.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.collection.component.paging_list.LazyGridAnime
import com.lelestacia.collection.component.paging_list.LazyListAnime
import com.lelestacia.common.DisplayStyle
import com.lelestacia.common.ui.purpleBlue
import com.lelestacia.model.Anime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreen(
    screenState: CollectionScreenState,
    onEvent: (CollectionScreenEvent) -> Unit,
    onAnimeClicked: (Anime) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagingAnime = screenState.anime.collectAsLazyPagingItems()
    val lazyGridState = rememberLazyGridState()
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
            ) {
                IconButton(onClick = { onEvent(CollectionScreenEvent.OnDisplayStyleOptionMenuChangedState) }) {
                    Icon(
                        imageVector = Icons.Filled.GridView,
                        contentDescription = "Display Style"
                    )
                    DropdownMenu(
                        expanded = screenState.isDisplayStyleOptionOpened,
                        onDismissRequest = { onEvent(CollectionScreenEvent.OnDisplayStyleOptionMenuChangedState) }) {
                        DropdownMenuItem(
                            text = {
                                Text(text = "Card")
                            },
                            onClick = {
                                onEvent(CollectionScreenEvent.OnDisplayStyleOptionMenuChangedState)
                                onEvent(
                                    CollectionScreenEvent.OnDisplayStyleChanged(
                                        DisplayStyle.CARD
                                    )
                                )
                            }
                        )
                        Divider()
                        DropdownMenuItem(
                            text = {
                                Text(text = "Compact Card")
                            },
                            onClick = {
                                onEvent(CollectionScreenEvent.OnDisplayStyleOptionMenuChangedState)
                                onEvent(
                                    CollectionScreenEvent.OnDisplayStyleChanged(
                                        DisplayStyle.COMPACT_CARD
                                    )
                                )
                            }
                        )
                        Divider()
                        DropdownMenuItem(
                            text = {
                                Text(text = "List")
                            },
                            onClick = {
                                onEvent(CollectionScreenEvent.OnDisplayStyleOptionMenuChangedState)
                                onEvent(
                                    CollectionScreenEvent.OnDisplayStyleChanged(
                                        DisplayStyle.LIST
                                    )
                                )
                            }
                        )
                    }
                }
                Divider()
            }
        },
        modifier = modifier
    ) { paddingValue ->

        when (pagingAnime.loadState.refresh) {
            is LoadState.Error -> Unit

            LoadState.Loading -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValue)
                ) {
                    CircularProgressIndicator(
                        color = purpleBlue
                    )
                }
                return@Scaffold
            }

            is LoadState.NotLoading -> Unit
        }

        if (screenState.displayStyle == DisplayStyle.LIST) {
            LazyListAnime(
                lazyListState = lazyListState,
                pagingAnime = pagingAnime,
                modifier = Modifier.padding(paddingValue),
                onAnimeClicked = onAnimeClicked
            )
        } else {
            LazyGridAnime(
                lazyGridState = lazyGridState,
                pagingAnime = pagingAnime,
                screenState = screenState,
                modifier = Modifier.padding(paddingValue),
                onAnimeClicked = onAnimeClicked
            )
        }
    }
}