package com.lelestacia.explore.screen.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.explore.R
import com.lelestacia.explore.component.HorizontalAnimeSection
import com.lelestacia.explore.component.SearchBarDashboard
import kotlinx.coroutines.launch

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    vm: ExploreViewModel = hiltViewModel(),
    onAnimeClicked: (Int) -> Unit,
    onMoreButtonClicked: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        SearchBarDashboard {

        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {}
        HorizontalAnimeSection(
            sectionTitle = stringResource(id = R.string.popular_anime),
            listOfAnime = vm.popularAnime.collectAsLazyPagingItems(),
            onMoreButtonClicked = {
                onMoreButtonClicked(1)
            },
            onAnimeClicked = { clickedAnime ->
                scope.launch {
                    vm.insertOrUpdateAnimeHistory(anime = clickedAnime).join()
                    onAnimeClicked(clickedAnime.malID)
                }
            }
        )
        HorizontalAnimeSection(
            sectionTitle = stringResource(id = R.string.airing_anime),
            listOfAnime = vm.airingAnime.collectAsLazyPagingItems(),
            onMoreButtonClicked = {
                onMoreButtonClicked(2)
            }, onAnimeClicked = { clickedAnime ->
                scope.launch {
                    vm.insertOrUpdateAnimeHistory(anime = clickedAnime).join()
                    onAnimeClicked(clickedAnime.malID)
                }
            })
        HorizontalAnimeSection(
            sectionTitle = stringResource(id = R.string.upcoming_anim),
            listOfAnime = vm.upcomingAnime.collectAsLazyPagingItems(),
            onMoreButtonClicked = {
                onMoreButtonClicked(3)
            },
            onAnimeClicked = { clickedAnime ->
                scope.launch {
                    vm.insertOrUpdateAnimeHistory(anime = clickedAnime).join()
                    onAnimeClicked(clickedAnime.malID)
                }
            }
        )
    }
}