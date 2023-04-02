package com.lelestacia.common.display_style

import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.lelestacia.common.DisplayStyle

@Composable
fun DisplayStyleMenu(
    isExpanded: Boolean,
    onStyleChanged: (DisplayStyle) -> Unit,
    onDismiss: () -> Unit
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = { onDismiss.invoke() }) {
        DropdownMenuItem(
            text = {
                Text(text = "Card")
            },
            onClick = {
                onDismiss.invoke()
                onStyleChanged(DisplayStyle.CARD)
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(text = "Compact Card")
            },
            onClick = {
                onDismiss.invoke()
                onStyleChanged(DisplayStyle.COMPACT_CARD)
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(text = "List")
            },
            onClick = {
                onDismiss.invoke()
                onStyleChanged(DisplayStyle.LIST)
            }
        )
    }
}