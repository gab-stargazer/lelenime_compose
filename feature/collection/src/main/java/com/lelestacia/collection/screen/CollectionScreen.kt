package com.lelestacia.collection.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.collection.state_and_event.CollectionScreenEvent
import com.lelestacia.collection.state_and_event.CollectionScreenState
import com.lelestacia.common.display_style.DisplayStyle
import com.lelestacia.common.display_style.DisplayStyleMenu
import com.lelestacia.common.lazy_anime.LazyGridAnime
import com.lelestacia.common.lazy_anime.LazyListAnime
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
               verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Collection",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    )
                    IconButton(onClick = { onEvent(CollectionScreenEvent.OnDisplayStyleOptionMenuChangedState) }) {
                        Icon(
                            imageVector = Icons.Filled.GridView,
                            contentDescription = "Display Style Button"
                        )
                        DisplayStyleMenu(
                            currentStyle = screenState.displayStyle,
                            isExpanded = screenState.isDisplayStyleOptionOpened,
                            onStyleChanged = { onEvent(CollectionScreenEvent.OnDisplayStyleChanged(it)) },
                            onDismiss = { onEvent(CollectionScreenEvent.OnDisplayStyleOptionMenuChangedState) }
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
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                return@Scaffold
            }

             is LoadState.NotLoading -> {
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
                        displayStyle = screenState.displayStyle,
                        modifier = Modifier.padding(paddingValue),
                        onAnimeClicked = onAnimeClicked
                    )
                }
            }
        }
    }
}