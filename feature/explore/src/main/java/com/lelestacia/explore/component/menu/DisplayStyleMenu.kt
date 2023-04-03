package com.lelestacia.explore.component.menu

import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.lelestacia.common.display_style.DisplayStyle
import com.lelestacia.explore.state_and_event.ExploreScreenEvent
import com.lelestacia.explore.state_and_event.ExploreScreenState

@Composable
fun DisplayStyleMenu(
    screenState: ExploreScreenState,
    onEvent: (ExploreScreenEvent) -> Unit,
) {
    DropdownMenu(
        expanded = screenState.headerScreenState.isDisplayStyleOptionOpened,
        onDismissRequest = { onEvent(ExploreScreenEvent.OnDisplayStyleOptionMenuChangedState) }) {
        DropdownMenuItem(
            text = {
                Text(text = "Card")
            },
            onClick = {
                onEvent(ExploreScreenEvent.OnDisplayStyleOptionMenuChangedState)
                onEvent(
                    ExploreScreenEvent.OnDisplayStyleChanged(
                        DisplayStyle.CARD
                    )
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(text = "Compact Card")
            },
            onClick = {
                onEvent(ExploreScreenEvent.OnDisplayStyleOptionMenuChangedState)
                onEvent(
                    ExploreScreenEvent.OnDisplayStyleChanged(
                        DisplayStyle.COMPACT_CARD
                    )
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(text = "List")
            },
            onClick = {
                onEvent(ExploreScreenEvent.OnDisplayStyleOptionMenuChangedState)
                onEvent(
                    ExploreScreenEvent.OnDisplayStyleChanged(
                        DisplayStyle.LIST
                    )
                )
            }
        )
    }
}