package com.lelestacia.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.lelestacia.common.Resource
import com.lelestacia.common.ui.theme.purpleBlue
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    animeID: Int,
    vm: DetailViewModel = hiltViewModel()
) {
    val animeResource = vm.anime.collectAsState()
    LaunchedEffect(key1 = animeResource.value) {
        if (animeResource.value is Resource.None) {
            vm.getAnimeByAnimeID(animeID = animeID)
        }
    }
    val anime = remember {
        animeResource.value.data
    }
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = anime?.isFavorite) {
        scope.launch {
            snackbarHostState.showSnackbar("Anime favorite is ${anime?.isFavorite}")
        }
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
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Navigation Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                navHostController.popBackStack()
                            }
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = purpleBlue
                ),
                scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
            )
        },
        floatingActionButton = {
            anime?.let {
                FloatingActionButton(
                    modifier = Modifier.padding(16.dp),
                    containerColor = purpleBlue,
                    onClick = {
                        vm.updateAnimeByAnimeID(animeID = animeID)
                    }) {
                    Icon(
                        imageVector = if (it.isFavorite) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Outlined.Favorite
                        },
                        tint = Color.White,
                        contentDescription = "Favorite Button"
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->

        anime?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(data = anime.coverImages)
                            .memoryCachePolicy(CachePolicy.ENABLED)
                            .crossfade(enable = true)
                            .build(),
                        contentDescription = "Cover Image from Anime ${anime.title}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .blur(radius = 12.dp)
                    )

                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(data = anime.coverImages)
                            .memoryCachePolicy(CachePolicy.ENABLED)
                            .crossfade(enable = true)
                            .build(),
                        contentDescription = "Cover Image from Anime ${anime.title}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(200.dp)
                            .width(150.dp)
                            .offset(y = 50.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }

                Spacer(modifier = Modifier.height(64.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = anime.title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    anime.titleJapanese?.let { titleJapanese ->
                        Text(
                            text = titleJapanese,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 8.dp),
                            fontFamily = FontFamily.SansSerif,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            0.dp,
                            alignment = Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.rank_and_score,
                                anime.rank,
                                anime.score ?: "Unknown"
                            ),
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(top = 8.dp),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Icon(imageVector = Icons.Filled.Star, contentDescription = "Star Icon")
                    }

                }
            }
        }
    }
}