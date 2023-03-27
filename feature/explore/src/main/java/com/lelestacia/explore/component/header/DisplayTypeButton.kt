package com.lelestacia.explore.component.header

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.common.ui.theme.purpleBlue
import com.lelestacia.explore.screen.explore.DisplayType

@Composable
fun DisplayTypeButton(
    isActive: Boolean,
    displayType: DisplayType,
    icon: ImageVector,
    onClicked: (DisplayType) -> Unit,
) {
    Button(
        onClick = {
            onClicked(displayType)
        },
        shape = RoundedCornerShape(24.dp),
        contentPadding = PaddingValues(
            horizontal = 12.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor =
            if (isActive) purpleBlue
            else Color.Transparent
        ),
        border = BorderStroke(
            width = 1.dp,
            color =
            if (isActive) {
                Color.Transparent
            } else {
                if (isSystemInDarkTheme()) Color.White
                else Color.Black.copy(
                    alpha = 0.5f
                )
            }
        ),
        modifier = Modifier,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint =
            if (isSystemInDarkTheme()) {
                Color.White
            } else {
                if (isActive) {
                    Color.White
                } else {
                    Color.Black
                }
            }
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = displayType.name,
            color =
            if (isSystemInDarkTheme()) {
                Color.White
            } else {
                if (isActive) {
                    Color.White
                } else {
                    Color.Black
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewDisplayTYpeButtonActive() {
    DisplayTypeButton(
        isActive = true,
        displayType = DisplayType.POPULAR,
        icon = Icons.Filled.Favorite,
        onClicked = {}
    )
}

@Preview
@Composable
fun PreviewDisplayTYpeButtonInactive() {
    DisplayTypeButton(
        isActive = false,
        displayType = DisplayType.POPULAR,
        icon = Icons.Filled.Favorite,
        onClicked = {}
    )
}
