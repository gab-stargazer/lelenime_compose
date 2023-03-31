package com.lelestacia.explore.component.paging_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.lelestacia.common.item_anime.AnimeCard
import com.lelestacia.common.item_anime.AnimeCardCompact
import com.lelestacia.common.DisplayStyle
import com.lelestacia.explore.screen.ExploreScreenState
import com.lelestacia.model.Anime

@Composable
fun LazyGridAnime(
    lazyGridState: LazyGridState,
    pagingAnime: LazyPagingItems<Anime>,
    screenState: ExploreScreenState,
    modifier: Modifier = Modifier,
    onAnimeClicked: (Anime) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        state = lazyGridState,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
            .fillMaxSize()
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
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            is LoadState.NotLoading -> Unit
        }
    }
}