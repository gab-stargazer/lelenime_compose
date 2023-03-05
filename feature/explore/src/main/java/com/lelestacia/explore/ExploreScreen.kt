package com.lelestacia.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.explore.component.HorizontalAnimeSection
import com.lelestacia.explore.component.SearchBarDashboard
import com.lelestacia.model.Anime
import kotlinx.coroutines.launch

@Composable
fun ExploreScreen(
    snackBarHostState: SnackbarHostState,
    vm: ExploreViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val airingAnime: LazyPagingItems<Anime> =
        vm.airingAnime.collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState),
    ) {
        SearchBarDashboard {

        }
        HorizontalAnimeSection(
            sectionTitle = "Airing Anime",
            listOfAnime = airingAnime,
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
    }
}