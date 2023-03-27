package com.lelestacia.explore.screen.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.explore.component.AnimeCard
import com.lelestacia.explore.component.header.DisplayTypeRow
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = com.lelestacia.common.R.drawable.lelenime),
                        contentDescription = "Lelenime",
                        modifier = Modifier
                            .height(48.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {

                        }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Anime"
                        )
                    }
                    IconButton(
                        onClick = {

                        }) {
                        Icon(
                            imageVector = Icons.Filled.FilterList,
                            contentDescription = "Filter Anime"
                        )
                    }
                }
                DisplayTypeRow(
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
                    AnimeCard(
                        anime = anime,
                        onAnimeClicked = { clickedAnime ->
                            onAnimeClicked(clickedAnime)
                        })
                }
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