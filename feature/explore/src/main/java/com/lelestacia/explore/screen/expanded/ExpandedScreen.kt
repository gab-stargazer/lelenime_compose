package com.lelestacia.explore.screen.expanded

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.lelestacia.explore.component.ListAnimeItem
import com.lelestacia.explore.screen.explore.ExploreViewModel
import com.lelestacia.model.Anime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ExpandedScreen(
    modifier: Modifier = Modifier,
    type: Int,
    vm: ExploreViewModel = hiltViewModel(),
    scope: CoroutineScope = rememberCoroutineScope(),
    onAnimeClicked: (Int) -> Unit
) {
    val lazyListAnime: LazyPagingItems<Anime> = when (type) {
        1 -> vm.popularAnime.collectAsLazyPagingItems()
        2 -> vm.airingAnime.collectAsLazyPagingItems()
        else -> vm.upcomingAnime.collectAsLazyPagingItems()
    }
    val lazyListState = rememberLazyListState()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (lazyListAnime.loadState.refresh) {
            is LoadState.Error -> {

            }

            LoadState.Loading -> {
                CircularProgressIndicator()
            }

            is LoadState.NotLoading -> {
                LazyColumn(state = lazyListState) {

                    items(
                        items = lazyListAnime,
                        key = {
                            it.malID
                        }
                    ) { pagingItem: Anime? ->
                        pagingItem?.let { anime: Anime ->
                            ListAnimeItem(
                                anime = anime,
                                onAnimeClicked = { clickedAnime: Anime ->
                                    scope.launch {
                                        vm.insertOrUpdateAnimeHistory(anime = clickedAnime).join()
                                        onAnimeClicked(anime.malID)
                                    }
                                }
                            )
                            Divider()
                        }
                    }

                    when (lazyListAnime.loadState.append) {
                        is LoadState.Error -> Unit
                        LoadState.Loading -> Unit
                        is LoadState.NotLoading -> Unit
                    }
                }
            }
        }
    }
}