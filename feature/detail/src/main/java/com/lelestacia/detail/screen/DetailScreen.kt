package com.lelestacia.detail.screen

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lelestacia.common.Resource
import com.lelestacia.detail.component.AnimeCoverImage
import com.lelestacia.detail.component.AnimeInformation
import com.lelestacia.detail.component.AnimeTitleAndScore
import com.lelestacia.model.Anime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedCrossfadeTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    animeID: Int,
    anime: Resource<Anime>,
    initiate: (Int) -> Unit,
    updateAnimeByAnimeID: (Int) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scrollState = rememberScrollState()

    var isDataInitiated by remember {
        mutableStateOf(false)
    }
    if (!isDataInitiated) {
        initiate(animeID)
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
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                    containerColor = Color.Transparent,
                    titleContentColor =
                    if (scrollState.value == 0) {
                        Color.White
                    } else {
                        if (isSystemInDarkTheme()) Color.White
                        else Color.Black
                    },
                    navigationIconContentColor =
                    if (scrollState.value == 0) {
                        Color.White
                    } else {
                        if (isSystemInDarkTheme()) Color.White
                        else Color.Black
                    }
                ),
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            when (anime) {
                is Resource.Success -> {
                    FloatingActionButton(
                        modifier = Modifier
                            .padding(16.dp),
                        onClick = {
                            updateAnimeByAnimeID(animeID)
                        },
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Crossfade(targetState = anime.data?.isFavorite, label = "") {
                            Icon(
                                imageVector =
                                if (anime.data?.isFavorite as Boolean) {
                                    Icons.Default.Favorite
                                } else {
                                    Icons.Default.FavoriteBorder
                                },
                                tint = Color.White,
                                contentDescription = "Favorite Button"
                            )
                        }
                    }
                }

                else -> Unit
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)

                }
            }

            else -> Unit
        }
    }
}