package com.lelestacia.explore.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun AnimeCardLoading() {
    Image(
        painter = painterResource(id = com.lelestacia.common.R.drawable.placeholder),
        contentDescription = "Placeholder Image",
        modifier = Modifier
            .height(134.dp)
            .width(100.dp)
            .clip(RoundedCornerShape(8.dp))
            .shimmer(),
        contentScale = ContentScale.Crop
    )
}