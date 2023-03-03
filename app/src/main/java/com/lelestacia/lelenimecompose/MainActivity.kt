package com.lelestacia.lelenimecompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lelestacia.collection.CollectionScreen
import com.lelestacia.common.Screen
import com.lelestacia.explore.ExploreScreen
import com.lelestacia.lelenimecompose.ui.component.BottomNav
import com.lelestacia.lelenimecompose.ui.theme.LelenimeComposeTheme
import com.lelestacia.more.MoreScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LelenimeComposeTheme {
                val navHostController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNav(navController = navHostController)
                    }
                ) {
                    NavHost(
                        navController = navHostController,
                        startDestination = Screen.Explore.route
                    ) {

                        composable(route = Screen.Explore.route) {
                            ExploreScreen()
                        }

                        composable(route = Screen.Collection.route) {
                            CollectionScreen()
                        }

                        composable(route = Screen.More.route) {
                            MoreScreen()
                        }
                    }
                }
            }
        }
    }
}