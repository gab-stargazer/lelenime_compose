package com.lelestacia.explore.component.paging_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.lelestacia.common.ui.theme.purpleBlue
import com.lelestacia.explore.component.anime_card.AnimeList
import com.lelestacia.model.Anime


@Composable
fun LazyListAnime(
    lazyListState: LazyListState?,
    pagingAnime: LazyPagingItems<Anime>,
    modifier: Modifier = Modifier,
    onAnimeClicked: (Anime) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        state = lazyListState ?: rememberLazyListState(),
        modifier = modifier
    ) {
        items(items = pagingAnime, key = { it.malID }) { pagingAnime ->
            pagingAnime?.let { anime ->
                AnimeList(anime = anime, onAnimeClicked = onAnimeClicked)
                Divider()
            }
        }

        when (val appending = pagingAnime.loadState.append) {
            is LoadState.Error -> {
                item {
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
                item {
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