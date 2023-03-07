package com.lelestacia.lelenimecompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lelestacia.common.route.Screen
import com.lelestacia.common.ui.theme.purpleBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(navHostController: NavHostController) {
    val currentDestination by navHostController.currentBackStackEntryAsState()
    val listOfRootDestination: List<String> = listOf(
        Screen.Explore.route,
        Screen.Collection.route,
        Screen.More.route
    )
    if (!listOfRootDestination.contains(currentDestination?.destination?.route)) {
        TopAppBar(
            title = {
                Text(
                    text = navHostController.currentDestination?.displayName ?: "Unknown Page",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Navigation Icon",
                    modifier = Modifier.clickable {
                        navHostController.popBackStack()
                    }
                )
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor =
                if (isSystemInDarkTheme()) {
                    purpleBlue.copy(alpha = 0.5f)
                } else {
                    purpleBlue
                },
                navigationIconContentColor = Color.White,
                titleContentColor = Color.White
            )
        )
    }
}