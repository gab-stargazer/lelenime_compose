package com.lelestacia.explore.component.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MovieFilter
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.explore.screen.explore.DisplayType
import com.lelestacia.explore.screen.explore.ExploreScreenEvent
import com.lelestacia.explore.screen.explore.ExploreScreenState

@Composable
fun DashboardDisplayTypeHeader(
    state: ExploreScreenState,
    onEvent: (ExploreScreenEvent) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DisplayTypeButton(
            isActive = state.displayType == DisplayType.POPULAR,
            displayType = DisplayType.POPULAR,
            icon = Icons.Filled.Favorite,
            onClicked = {
                onEvent(ExploreScreenEvent.OnDisplayTypeChanged(DisplayType.POPULAR))
            }
        )
        DisplayTypeButton(
            isActive = state.displayType == DisplayType.AIRING,
            displayType = DisplayType.AIRING,
            icon = Icons.Filled.MovieFilter,
            onClicked = {
                onEvent(ExploreScreenEvent.OnDisplayTypeChanged(DisplayType.AIRING))
            }
        )
        DisplayTypeButton(
            isActive = state.displayType == DisplayType.UPCOMING,
            displayType = DisplayType.UPCOMING,
            icon = Icons.Filled.Upcoming,
            onClicked = {
                onEvent(ExploreScreenEvent.OnDisplayTypeChanged(DisplayType.UPCOMING))
            }
        )
    }
}

@Preview
@Composable
fun PreviewTopHeader() {
    DashboardDisplayTypeHeader(
        state = ExploreScreenState(),
        onEvent = {}
    )
}