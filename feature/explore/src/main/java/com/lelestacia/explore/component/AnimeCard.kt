package com.lelestacia.explore.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.lelestacia.model.Anime

@Composable
fun AnimeCard(
    anime: Anime,
    onAnimeClicked: (Anime) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.animateContentSize()
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
        )
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