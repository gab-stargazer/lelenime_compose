package com.lelestacia.more.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lelestacia.common.display_style.DisplayStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    state: SettingScreenState,
    onEvent: (SettingScreenEvent) -> Unit,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                }
            )
        }
    ) { paddingValue ->
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Display Style Preferences",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = state.displayStylePreferences.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                IconButton(onClick = { onEvent(SettingScreenEvent.DisplayStylePreferencesMenuStateChanged) }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )
                    DropdownMenu(
                        expanded = state.isDisplayStylePreferencesMenuOpened,
                        onDismissRequest = { onEvent(SettingScreenEvent.DisplayStylePreferencesMenuStateChanged) }) {

                        DropdownMenuItem(
                            text = {
                                Text(text = "Card")
                            },
                            onClick = {
                                onEvent(
                                    SettingScreenEvent.UpdateDisplayStylePreferences(DisplayStyle.CARD)
                                )
                            })

                        DropdownMenuItem(
                            text = { Text(text = "Compact Card") },
                            onClick = {
                                onEvent(
                                    SettingScreenEvent.UpdateDisplayStylePreferences(DisplayStyle.COMPACT_CARD)
                                )
                            })

                        DropdownMenuItem(
                            text = { Text(text = "List") },
                            onClick = {
                                onEvent(
                                    SettingScreenEvent.UpdateDisplayStylePreferences(DisplayStyle.LIST)
                                )
                            })
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Dark Mode Preferences",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = when (state.darkModePreferences) {
                            1 -> "Auto"
                            2 -> "Day Mode"
                            else -> "Night Mode"
                        },
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                IconButton(onClick = { onEvent(SettingScreenEvent.DarkModePreferencesMenuStateChanged) }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )
                    DropdownMenu(
                        expanded = state.isDarkModePreferencesMenuOpened,
                        onDismissRequest = { onEvent(SettingScreenEvent.DarkModePreferencesMenuStateChanged) }) {

                        DropdownMenuItem(
                            text = {
                                Text(text = "Auto")
                            },
                            onClick = {
                                onEvent(
                                    SettingScreenEvent.UpdateDarkModePreferences(1)
                                )
                            })

                        DropdownMenuItem(
                            text = { Text(text = "Day Mode") },
                            onClick = {
                                onEvent(
                                    SettingScreenEvent.UpdateDarkModePreferences(2)
                                )
                            })

                        DropdownMenuItem(
                            text = { Text(text = "Dark Mode") },
                            onClick = {
                                onEvent(
                                    SettingScreenEvent.UpdateDarkModePreferences(3)
                                )
                            })
                    }
                }
            }
        }
    }
}