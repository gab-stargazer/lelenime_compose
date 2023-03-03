package com.lelestacia.common

sealed class Screen(val route: String) {
    object Explore : Screen("explore")
    object Collection : Screen("collection")
    object More : Screen("more")
}
