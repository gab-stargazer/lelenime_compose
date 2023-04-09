package com.lelestacia.more.screen.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lelestacia.common.route.Screen
import com.lelestacia.more.component.MoreScreenButton

@Composable
fun MoreScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        MoreScreenButton(
            text = "Settings",
            icon = Icons.Filled.Settings
        ) {
            navController.navigate(route = Screen.Settings.route) {
                restoreState = true
            }
        }
        MoreScreenButton(
            text = "About",
            icon = Icons.Filled.Info,
            onClick = {
                navController.navigate(route = Screen.About.route) {
                    restoreState = true
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewMoreScreen() {
    MoreScreen(navController = rememberNavController())
}