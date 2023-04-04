package com.lelestacia.detail.screen

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.lelestacia.common.Resource
import com.lelestacia.common.request_param.AnimeRating
import com.lelestacia.detail.component.AnimeInformation
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
                    if (isSystemInDarkTheme()) Color.White
                    else Color.Black,
                    navigationIconContentColor =
                    if (isSystemInDarkTheme()) Color.White
                    else Color.Black
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
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {

        val gradient = Brush.verticalGradient(
            colors = listOf(
                MaterialTheme.colorScheme.background.copy(
                    alpha = 0.5F
                ),
                MaterialTheme.colorScheme.background
            )
        )

        anime.data?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Box {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data(data = it.coverImages)
                                .memoryCachePolicy(CachePolicy.ENABLED)
                                .memoryCacheKey(it.malID.toString())
                                .diskCachePolicy(CachePolicy.ENABLED)
                                .diskCacheKey(it.malID.toString())
                                .allowHardware(false)
                                .allowRgb565(true)
                                .crossfade(enable = true)
                                .build(),
                            contentDescription = "Cover Image from Anime ${it.title}",
                            contentScale = ContentScale.Crop,
                            colorFilter = ColorFilter.colorMatrix(
                                colorMatrix = ColorMatrix().apply {
                                    setToSaturation(sat = 0.5F)
                                }),
                            filterQuality = FilterQuality.Medium,
                            alignment = Alignment.TopCenter,
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 300.dp)
                                .clip(RoundedCornerShape(4.dp))
                        )

                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clip(RoundedCornerShape(4.dp))
                                .background(gradient)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .offset(y = 64.dp)
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data(data = it.coverImages)
                                .memoryCachePolicy(CachePolicy.ENABLED)
                                .memoryCacheKey(it.malID.toString())
                                .diskCachePolicy(CachePolicy.ENABLED)
                                .diskCacheKey(it.malID.toString())
                                .allowHardware(false)
                                .allowRgb565(true)
                                .crossfade(enable = true)
                                .build(),
                            contentDescription = "Cover Image from Anime ${it.title}",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .widthIn(min = 100.dp, max = 150.dp)
                                .aspectRatio(3F / 4F)
                                .clip(RoundedCornerShape(4.dp))
                                .weight(1f)
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .weight(2f)
                                .padding(horizontal = 8.dp)
                        ) {
                            if (it.rating != AnimeRating.RX.title) {
                                Text(
                                    text = "#" + it.rank.toString(),
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        fontWeight = FontWeight.Black
                                    )
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
                                Text(
                                    text = (it.score ?: 0).toString(),
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Anime Score Icon"
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = (it.scoredBy ?: 0).toString(),
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                                Icon(imageVector = Icons.Filled.People, contentDescription = null)
                            }
                            Text(
                                text = it.title,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            it.titleJapanese?.let { titleJapanese ->
                                Text(
                                    text = titleJapanese,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector =
                                    when (it.status) {
                                        "Currently Airing" -> Icons.Filled.Schedule
                                        "Not yet aired" -> Icons.Filled.Close
                                        else -> Icons.Filled.Check
                                    },
                                    contentDescription = "Anime Status Icon"
                                )
                                Text(
                                    text = it.status,
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        }
                    }
                }
                AnimeInformation(anime = it)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it.synopsis ?: "No Synopsis Provided By Mal",
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}