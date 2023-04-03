package com.lelestacia.common.item_anime

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.lelestacia.common.display_style.DisplayStyle
import com.lelestacia.model.Anime

@Composable
fun AnimeCard(
    anime: Anime,
    displayStyle: DisplayStyle,
    onAnimeClicked: (Anime) -> Unit
) {
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }
    val gradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = sizeImage.height.toFloat() / 3,
        endY = sizeImage.height.toFloat()
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.animateContentSize()
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(data = anime.coverImages)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .memoryCacheKey(anime.malID.toString())
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .diskCacheKey(anime.malID.toString())
                    .allowHardware(false)
                    .allowRgb565(true)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = "Cover Image from Anime ${anime.title}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(width = 150.dp)
                    .aspectRatio(3f / 4f)
                    .clip(RoundedCornerShape(4.dp))
                    .clickable {
                        onAnimeClicked(anime)
                    }
                    .onGloballyPositioned {
                        sizeImage = it.size
                    }
            )
            if (displayStyle == DisplayStyle.COMPACT_CARD) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(4.dp))
                        .background(gradient)
                        .clickable {
                            onAnimeClicked(anime)
                        }
                )
                Text(
                    text = anime.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .widthIn(max = 140.dp)
                        .padding(horizontal = 5.dp)
                        .padding(bottom = 4.dp)
                )
            }
        }
        AnimatedVisibility(visible = displayStyle == DisplayStyle.CARD) {
            Text(
                text = anime.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .widthIn(max = 140.dp)
                    .padding(horizontal = 5.dp)
            )
        }
    }
}