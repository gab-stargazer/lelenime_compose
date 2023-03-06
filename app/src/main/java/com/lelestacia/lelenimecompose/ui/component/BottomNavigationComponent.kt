package com.lelestacia.lelenimecompose.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lelestacia.common.navItem
import com.lelestacia.common.purpleBlue

@Composable
fun BottomNav(navController: NavHostController, modifier: Modifier = Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(
        modifier = Modifier,
        containerColor = Color.White,
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
                        imageVector = navItem.icon, contentDescription = navItem.title
                    )
                },
                label = {
                    Text(text = navItem.title)
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    selectedIconColor = purpleBlue,
                    selectedTextColor = purpleBlue,
                    unselectedIconColor = purpleBlue.copy(
                        alpha = 0.75f
                    ),
                    unselectedTextColor = purpleBlue.copy(
                        alpha = 0.75f
                    )
                )
            )
        }
    }
}