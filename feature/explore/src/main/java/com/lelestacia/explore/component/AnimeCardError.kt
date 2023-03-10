package com.lelestacia.explore.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.lelestacia.common.ui.theme.purpleBlue

@Composable
fun AnimeCardError(
    modifier: Modifier = Modifier,
    errorMessage: String?,
    onRetryClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .height(134.dp)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorMessage ?: "Unknown Error",
            fontFamily = FontFamily.SansSerif,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .widthIn(max = 150.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetryClicked.invoke() },
            colors = ButtonDefaults.buttonColors(
                containerColor = purpleBlue
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Retry",
                color = Color.White
            )
        }
    }
}