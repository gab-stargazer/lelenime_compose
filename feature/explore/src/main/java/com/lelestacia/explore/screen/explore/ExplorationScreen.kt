package com.lelestacia.explore.screen.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import com.lelestacia.explore.component.anime_card.AnimeCard
import com.lelestacia.explore.component.anime_card.AnimeCardCompact
import com.lelestacia.explore.component.header.DashboardTopHeader
import com.lelestacia.explore.component.header.DashboardDisplayTypeHeader
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
    val popularScrollState = rememberLazyGridState()
    val airingScrollState = rememberLazyGridState()
    val upcomingScrollState = rememberLazyGridState()
    val scrollState =
        when (screenState.displayType) {
            DisplayType.POPULAR -> popularScrollState
            DisplayType.AIRING -> airingScrollState
            DisplayType.UPCOMING -> upcomingScrollState
        }

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
                    onEvent = {
                        onEvent(
                            ExploreScreenEvent.OnDisplayTypeChanged(it)
                        )
                    }
                )
                Divider(modifier = Modifier.fillMaxWidth())
            }
        },
        modifier = modifier
    ) { paddingValue ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            items(pagingAnime.itemCount) { index ->
                pagingAnime[index]?.let { anime ->
                    when (screenState.displayStyle) {
                        DisplayStyle.CARD -> {
                            AnimeCard(
                                anime = anime,
                                onAnimeClicked = { clickedAnime ->
                                    onAnimeClicked(clickedAnime)
                                })
                        }

                        else -> {
                            AnimeCardCompact(
                                anime = anime,
                                onAnimeClicked = { clickedAnime ->
                                    onAnimeClicked(clickedAnime)
                                }
                            )
                        }
                    }
                }
            }

            when (val appending = pagingAnime.loadState.append) {
                is LoadState.Error -> {
                    item(
                        span = {
                            GridItemSpan(3)
                        }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                        ) {
                            Text(text = appending.error.message ?: "")
                            Button(
                                onClick = { pagingAnime.retry() },
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(text = "Retry")
                            }
                        }
                    }
                }

                LoadState.Loading -> {
                    item(
                        span = {
                            GridItemSpan(3)
                        }) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                        ) {
                            CircularProgressIndicator(
                                color = purpleBlue
                            )
                        }
                    }
                }

                is LoadState.NotLoading -> Unit
            }
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