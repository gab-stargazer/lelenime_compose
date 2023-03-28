package com.lelestacia.explore.component.header

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.common.R
import com.lelestacia.explore.screen.explore.DisplayStyle
import com.lelestacia.explore.screen.explore.ExploreScreenEvent
import com.lelestacia.explore.screen.explore.ExploreScreenState

@Composable
fun DashboardTopHeader(
    screenState: ExploreScreenState,
    onEvent: (ExploreScreenEvent) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.lelenime),
            contentDescription = "Lelenime",
            modifier = Modifier
                .height(48.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {

            }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Anime"
            )
        }
        IconButton(
            onClick = {

            }) {
            Icon(
                imageVector = Icons.Filled.FilterList,
                contentDescription = "Filter Anime"
            )
        }
        IconButton(
            onClick = {
                onEvent(ExploreScreenEvent.OnDisplayStyleOptionMenuChangedState)
            }) {
            Icon(
                imageVector = Icons.Filled.GridView,
                contentDescription = "Display Style"
            )
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
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboardTopHeader() {
    Surface {
        DashboardTopHeader(
            screenState = ExploreScreenState(),
            onEvent = {}
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewDashboardTopHeaderDarkMode() {
    Surface {
        DashboardTopHeader(
            screenState = ExploreScreenState(),
            onEvent = {}
        )
    }
}