package com.lelestacia.detail.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lelestacia.common.Resource
import com.lelestacia.common.ui.theme.purpleBlue
import com.lelestacia.detail.component.AnimeCoverImage
import com.lelestacia.detail.component.AnimeInformation
import com.lelestacia.detail.component.AnimeTitleAndScore
import com.lelestacia.model.Anime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    animeID: Int,
    vm: DetailViewModel = hiltViewModel()
) {
    val animeResource = vm.anime.collectAsState()
    var isDataInitiated by remember {
        mutableStateOf(false)
    }
    if (!isDataInitiated) {
        vm.getAnimeByAnimeID(animeID = animeID)
        isDataInitiated = true
    }
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(
                        text = "Detail Anime",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.popBackStack()
                        }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigation Icon",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = purpleBlue
                ),
                scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
            )
        },
        floatingActionButton = {
            when (val animeFavorite = animeResource.value) {
                is Resource.Success -> {
                    FloatingActionButton(
                        modifier = Modifier
                            .padding(16.dp),
                        containerColor = purpleBlue,
                        onClick = {
                            vm.updateAnimeByAnimeID(animeID = animeID)
                        }) {
                        Icon(
                            imageVector =
                            if (animeFavorite.data?.isFavorite as Boolean) {
                                Icons.Default.Favorite
                            } else {
                                Icons.Default.FavoriteBorder
                            },
                            tint = Color.White,
                            contentDescription = "Favorite Button"
                        )
                    }
                }

                else -> Unit
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->

        when (animeResource.value) {
            is Resource.Success -> {
                val anime = animeResource.value.data as Anime
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(state = rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimeCoverImage(anime = anime)
                    Spacer(modifier = Modifier.height(64.dp))
                    AnimeTitleAndScore(anime = anime)
                    AnimeInformation(anime = anime)
                    Text(
                        text = anime.synopsis ?: "Unknown",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(top = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(48.dp))
                }
            }

            else -> Unit
        }
    }
}