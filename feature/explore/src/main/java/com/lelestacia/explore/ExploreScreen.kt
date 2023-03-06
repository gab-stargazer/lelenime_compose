package com.lelestacia.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.explore.component.HorizontalAnimeSection
import com.lelestacia.explore.component.SearchBarDashboard
import kotlinx.coroutines.launch

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    vm: ExploreViewModel = hiltViewModel()
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
            sectionTitle = "Trending Anime",
            listOfAnime = vm.popularAnime.collectAsLazyPagingItems(),
            onMoreButtonClicked = {

            },
            onAnimeClicked = {

            }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {}
        HorizontalAnimeSection(
            sectionTitle = "Airing Anime",
            listOfAnime = vm.airingAnime.collectAsLazyPagingItems(),
            onMoreButtonClicked = {

            }, onAnimeClicked = { clickedAnime ->
                scope.launch {
                    snackBarHostState.showSnackbar(
                        message = "Selected Anime is ${clickedAnime.title}",
                        actionLabel = "Ok",
                        duration = SnackbarDuration.Short
                    )
                }
            })
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {}
        HorizontalAnimeSection(
            sectionTitle = "Upcoming Anime",
            listOfAnime = vm.upcomingAnime.collectAsLazyPagingItems(),
            onMoreButtonClicked = {

            },
            onAnimeClicked = {

            }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {}
    }
}