package com.lelestacia.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lelestacia.detail.R
import com.lelestacia.model.Anime

@Composable
fun AnimeTitleAndScore(anime: Anime) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = anime.title,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 12.dp),
            fontFamily = FontFamily.SansSerif,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        if (anime.titleJapanese != null && anime.titleJapanese != anime.title) {
            Text(
                text = anime.titleJapanese!!,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 12.dp),
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
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
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(end = 4.dp)
            )
            Icon(imageVector = Icons.Filled.Star, contentDescription = "Star Icon")
        }
        Text(
            text = stringResource(id = R.string.rated_by, anime.scoredBy ?: 0),
            style = MaterialTheme.typography.titleSmall
        )
    }
}