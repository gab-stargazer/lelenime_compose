package com.lelestacia.explore.component.header

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.explore.screen.DisplayType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayTypeButton(
    isActive: Boolean,
    displayType: DisplayType,
    icon: ImageVector,
    onClicked: (DisplayType) -> Unit,
) {
    AssistChip(
        onClick = { onClicked(displayType) },
        label = {
            Text(
                text = displayType.name,
//                color =
//                if (isSystemInDarkTheme()) {
//                    Color.White
//                } else {
//                    if (isActive) {
//                        Color.White
//                    } else {
//                        Color.Black
//                    }
//                }
            )
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
//                tint =
//                if (isActive) {
//                    Color.White
//                } else {
//                    Color.Black
//                }
            )
        },
//        colors =  AssistChipDefaults.assistChipColors(
//            containerColor =
//            if (isActive) purpleBlue
//            else Color.Transparent
//        ),
//        border = AssistChipDefaults.assistChipBorder(
//            borderColor =
//            if (isActive) {
//                Color.Transparent
//            } else {
//                if (isSystemInDarkTheme()) Color.White
//                else Color.Black.copy(
//                    alpha = 0.5f
//                )
//            }
//        )
    )
}

@Preview
@Composable
fun PreviewDisplayTypeButtonActive() {
    DisplayTypeButton(
        isActive = true,
        displayType = DisplayType.POPULAR,
        icon = Icons.Filled.Favorite,
        onClicked = {}
    )
}

@Preview
@Composable
fun PreviewDisplayTypeButtonInactive() {
    DisplayTypeButton(
        isActive = false,
        displayType = DisplayType.POPULAR,
        icon = Icons.Filled.Favorite,
        onClicked = {}
    )
}
