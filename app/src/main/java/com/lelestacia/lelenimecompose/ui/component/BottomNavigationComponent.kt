package com.lelestacia.lelenimecompose.ui.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lelestacia.common.navItem
import com.lelestacia.common.route.Screen
import com.lelestacia.common.ui.theme.purpleBlue

@Composable
fun BottomNav(navController: NavHostController) {
    val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
    val currentRoute: String? = navBackStackEntry?.destination?.route
    val listOfRootDestination: List<String> = listOf(
        Screen.Explore.route,
        Screen.Collection.route,
        Screen.More.route
    )
    if (listOfRootDestination.contains(currentRoute)) {
        NavigationBar(
            modifier = Modifier,
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            navItem.map { navItem ->
                NavigationBarItem(
                    selected = currentRoute == navItem.screen.route,
                    onClick = {
                        navController.navigate(navItem.title) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector =
                            if (currentRoute == navItem.screen.route)
                                navItem.iconActive
                            else navItem.iconInactive,
                            contentDescription = navItem.title
                        )
                    },
                    label = {
                        Text(text = navItem.title)
                    },
                    alwaysShowLabel = false,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor =
                        if (isSystemInDarkTheme()) {
                            purpleBlue
                        } else {
                            purpleBlue.copy(alpha = 0.1f)
                        },
                        selectedIconColor = Color.White
                    )
                )
            }
        }
    }
}