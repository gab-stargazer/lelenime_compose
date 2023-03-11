package com.lelestacia.lelenimecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lelestacia.collection.CollectionScreen
import com.lelestacia.common.route.Screen
import com.lelestacia.detail.screen.DetailScreen
import com.lelestacia.explore.screen.expanded.ExpandedScreen
import com.lelestacia.explore.screen.explore.ExploreScreen
import com.lelestacia.lelenimecompose.ui.component.LeleNimeBottomBar
import com.lelestacia.lelenimecompose.ui.theme.LelenimeComposeTheme
import com.lelestacia.more.MoreScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LelenimeComposeTheme {
                val navHostController: NavHostController = rememberNavController()
                val snackBarHostState: SnackbarHostState = remember {
                    SnackbarHostState()
                }
                Scaffold(
                    bottomBar = {
                        LeleNimeBottomBar(navController = navHostController)
                    },
                    snackbarHost = {
                        SnackbarHost(snackBarHostState)
                    }
                ) { paddingValue ->
                    NavHost(
                        navController = navHostController,
                        startDestination = Screen.Explore.route
                    ) {

                        composable(route = Screen.Explore.route) {
                            ExploreScreen(
                                modifier = Modifier.padding(paddingValue),
                                onAnimeClicked = { animeID ->
                                    navHostController.navigate(
                                        route = Screen.DetailAnimeScreen.createRoute(animeID = animeID)
                                    ) {
                                        popUpTo(
                                            navHostController.currentDestination?.id
                                                ?: navHostController.graph.startDestinationId
                                        ) {
                                            saveState = true
                                        }
                                        restoreState = true
                                        launchSingleTop = true
                                    }
                                },
                                onMoreButtonClicked = { animeType ->
                                    navHostController.navigate(
                                        route = Screen.ExploreExpanded.createRoute(animeType = animeType)
                                    ) {
                                        popUpTo(
                                            navHostController.currentDestination?.id
                                                ?: navHostController.graph.startDestinationId
                                        ) {
                                            saveState = true
                                        }
                                        restoreState = true
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable(
                            route = Screen.ExploreExpanded.route,
                            arguments = listOf(
                                navArgument("anime_type") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            ExpandedScreen(
                                type = it.arguments?.getInt("anime_type") ?: 1
                            ) { animeID ->
                                navHostController.navigate(
                                    route = Screen.DetailAnimeScreen.createRoute(animeID = animeID)
                                ) {
                                    popUpTo(
                                        navHostController.currentDestination?.id
                                            ?: navHostController.graph.startDestinationId
                                    ) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            }
                        }

                        composable(route = Screen.Collection.route) {
                            CollectionScreen(
                                modifier = Modifier.padding(paddingValue)
                            )
                        }

                        composable(route = Screen.More.route) {
                            MoreScreen(
                                modifier = Modifier.padding(paddingValue)
                            )
                        }

                        composable(
                            route = Screen.DetailAnimeScreen.route,
                            arguments = listOf(
                                navArgument(name = "mal_id") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            DetailScreen(
                                animeID = it.arguments?.getInt("mal_id") ?: 0,
                                navHostController = navHostController
                            )
                        }
                    }
                }
            }
        }
    }
}
