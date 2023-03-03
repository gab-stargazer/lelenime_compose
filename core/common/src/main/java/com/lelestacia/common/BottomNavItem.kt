package com.lelestacia.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)

val navItem: List<BottomNavItem> = listOf(
    BottomNavItem(
        title = "Explore",
        icon = Icons.Default.Home,
        screen = Screen.Explore
    ),
    BottomNavItem(
        title = "Collection",
        icon = Icons.Default.Person,
        screen = Screen.Collection
    ),
    BottomNavItem(
        title = "More",
        icon = Icons.Default.Settings,
        screen = Screen.More
    )
)
