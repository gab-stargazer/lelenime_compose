package com.lelestacia.lelenimecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lelestacia.collection.screen.CollectionScreen
import com.lelestacia.collection.screen.CollectionScreenViewModel
import com.lelestacia.common.route.Screen
import com.lelestacia.detail.screen.DetailScreen
import com.lelestacia.detail.screen.DetailViewModel
import com.lelestacia.explore.screen.ExplorationScreen
import com.lelestacia.explore.screen.ExplorationScreenViewModel
import com.lelestacia.lelenimecompose.ui.component.LeleNimeBottomBar
import com.lelestacia.lelenimecompose.ui.theme.LelenimeComposeTheme
import com.lelestacia.more.screen.about.AboutScreen
import com.lelestacia.more.screen.more.MoreScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LelenimeComposeTheme {
                val scope: CoroutineScope = rememberCoroutineScope()
                val uiController = rememberSystemUiController()
                val navController: NavHostController = rememberAnimatedNavController()
                Scaffold(
                    bottomBar = {
                        LeleNimeBottomBar(navController = navController)
                    }
                ) { paddingValue ->
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Screen.Explore.route,
                    ) {

                        composable(route = Screen.Explore.route) {
                            uiController.setStatusBarColor(
                                color = MaterialTheme.colorScheme.background,
                                darkIcons = !isSystemInDarkTheme()
                            )
                            val viewModel = hiltViewModel<ExplorationScreenViewModel>()
                            val uiState by viewModel.explorationScreenState.collectAsState()
                            ExplorationScreen(
                                screenState = uiState,
                                onEvent = viewModel::onEvent,
                                onAnimeClicked = { anime ->
                                    scope.launch {
                                        viewModel.insertOrUpdateAnimeHistory(anime = anime).join()
                                        navController.navigate(
                                            route = Screen.DetailAnimeScreen.createRoute(
                                                anime.malID
                                            )
                                        ) {
                                            restoreState = true
                                        }
                                    }
                                },
                                modifier = Modifier.padding(paddingValue)
                            )
                        }

                        composable(route = Screen.Collection.route) {
                            uiController.setStatusBarColor(
                                color = MaterialTheme.colorScheme.background,
                                darkIcons = !isSystemInDarkTheme()
                            )
                            val viewModel = hiltViewModel<CollectionScreenViewModel>()
                            val uiState by viewModel.collectionScreenState.collectAsState()
                            CollectionScreen(
                                screenState = uiState,
                                onEvent = viewModel::onEvent,
                                onAnimeClicked = { anime ->
                                    scope.launch {
                                        viewModel.insertOrUpdateAnimeHistory(anime = anime).join()
                                        navController.navigate(
                                            route = Screen.DetailAnimeScreen.createRoute(
                                                anime.malID
                                            )
                                        )
                                    }
                                },
                                modifier = Modifier.padding(paddingValue)
                            )
                        }

                        composable(route = Screen.More.route) {
                            uiController.setStatusBarColor(
                                color = MaterialTheme.colorScheme.background,
                                darkIcons = !isSystemInDarkTheme()
                            )
                            MoreScreen(
                                navController = navController,
                                modifier = Modifier.padding(paddingValue)
                            )
                        }

                        composable(route = Screen.About.route) {
                            uiController.setStatusBarColor(
                                color = MaterialTheme.colorScheme.background,
                                darkIcons = !isSystemInDarkTheme()
                            )
                            AboutScreen(navController = navController)
                        }

                        composable(
                            route = Screen.DetailAnimeScreen.route,
                            arguments = listOf(
                                navArgument(name = "mal_id") {
                                    type = NavType.IntType
                                }
                              ),
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentScope.SlideDirection.Up,
                                    animationSpec = tween(500)
                                ) + fadeIn(
                                    animationSpec = tween(500)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentScope.SlideDirection.Down,
                                    animationSpec = tween(500)
                                ) + fadeOut(
                                    animationSpec = tween(500)
                                )
                            }
                        ) {
                            uiController.setStatusBarColor(
                                color = MaterialTheme.colorScheme.background,
                                darkIcons = !isSystemInDarkTheme()
                            )

                            val viewModel = hiltViewModel<DetailViewModel>()
                            val animeResource by viewModel.anime.collectAsState()

                            DetailScreen(
                                animeID = it.arguments?.getInt("mal_id") ?: 0,
                                navHostController = navController,
                                anime = animeResource,
                                initiate = viewModel::getAnimeByAnimeID,
                                updateAnimeByAnimeID = viewModel::updateAnimeByAnimeID
                            )
                        }
                    }
                }
            }
        }
    }
}
