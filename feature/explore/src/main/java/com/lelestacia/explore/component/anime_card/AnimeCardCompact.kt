package com.lelestacia.explore.component.anime_card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import com.lelestacia.model.Anime

@Composable
fun AnimeCardCompact(
    anime: Anime,
    onAnimeClicked: (Anime) -> Unit
) {
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }
    val gradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = sizeImage.height.toFloat() / 4,
        endY = sizeImage.height.toFloat()
    )

    Box(
        contentAlignment = Alignment.BottomStart,
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