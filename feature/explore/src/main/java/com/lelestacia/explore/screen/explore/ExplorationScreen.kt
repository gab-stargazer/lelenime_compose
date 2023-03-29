package com.lelestacia.explore.screen.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.common.ui.theme.purpleBlue
import com.lelestacia.explore.component.header.DashboardDisplayTypeHeader
import com.lelestacia.explore.component.header.DashboardTopHeader
import com.lelestacia.explore.component.paging_list.LazyGridAnime
import com.lelestacia.explore.component.paging_list.LazyListAnime
import com.lelestacia.model.Anime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExplorationScreen(
    screenState: ExploreScreenState,
    onEvent: (ExploreScreenEvent) -> Unit,
    onAnimeClicked: (Anime) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagingAnime: LazyPagingItems<Anime> = screenState.anime.collectAsLazyPagingItems()
    val listOfLazyGridState: Map<DisplayType, LazyGridState> = mapOf(
        Pair(DisplayType.POPULAR, rememberLazyGridState()),
        Pair(DisplayType.AIRING, rememberLazyGridState()),
        Pair(DisplayType.UPCOMING, rememberLazyGridState())
    )
    val listOfLazyListState: Map<DisplayType, LazyListState> = mapOf(
        Pair(DisplayType.POPULAR, rememberLazyListState()),
        Pair(DisplayType.AIRING, rememberLazyListState()),
        Pair(DisplayType.UPCOMING, rememberLazyListState())
    )
    val lazyGridState = listOfLazyGridState[screenState.displayType]
    val lazyListState = listOfLazyListState[screenState.displayType]

    Scaffold(
        topBar = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                DashboardTopHeader(
                    screenState = screenState,
                    onEvent = onEvent
                )
                DashboardDisplayTypeHeader(
                    state = screenState,
                    onEvent = onEvent
                )
                Divider(modifier = Modifier.fillMaxWidth())
            }
        },
        modifier = modifier
    ) { paddingValue ->

        when (val refreshing = pagingAnime.loadState.refresh) {
            is LoadState.Error -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValue),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 8.dp,
                        alignment = Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = refreshing.error.message ?: "")
                    Button(
                        onClick = { pagingAnime.retry() },
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(text = "Retry")
                    }
                }
                return@Scaffold
            }

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
                lazyGridState = lazyGridState ?: rememberLazyGridState(),
                pagingAnime = pagingAnime,
                screenState = screenState,
                modifier = Modifier.padding(paddingValue),
                onAnimeClicked = onAnimeClicked
            )
        }
    }
}

@Preview
@Composable
fun PreviewExplorationScreen() {
    ExplorationScreen(
        screenState = ExploreScreenState(),
        onEvent = {},
        onAnimeClicked = {}
    )
}