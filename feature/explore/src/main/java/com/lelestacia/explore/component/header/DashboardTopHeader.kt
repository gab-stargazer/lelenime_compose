package com.lelestacia.explore.component.header

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.explore.screen.explore.DisplayStyle
import com.lelestacia.explore.screen.explore.DisplayType
import com.lelestacia.explore.screen.explore.ExploreScreenEvent
import com.lelestacia.explore.screen.explore.ExploreScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopHeader(
    screenState: ExploreScreenState,
    onEvent: (ExploreScreenEvent) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (screenState.headerScreenState.isSearching) {
            TextField(
                value = screenState.headerScreenState.searchQuery,
                onValueChange = { newSearchQuery ->
                    onEvent(ExploreScreenEvent.OnSearchQueryChanged(newSearchQuery))
                },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = "Insert Anime Title")
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions {
                    onEvent(ExploreScreenEvent.OnDisplayTypeChanged(DisplayType.SEARCH))
                }
            )
        } else {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    onEvent(ExploreScreenEvent.OnStartSearching)
                }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Anime"
                )
            }
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
            screenState = ExploreScreenState(
                headerScreenState = HeaderScreenState(
                    searchQuery = "Test",
                    isSearching = true
                )
            ),
            onEvent = {}
        )
    }
}