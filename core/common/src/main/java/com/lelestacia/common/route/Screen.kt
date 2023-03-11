package com.lelestacia.common.route

sealed class Screen(val route: String) {
    object Explore : Screen("explore")
    object ExploreExpanded : Screen("explore/expanded/{anime_type}") {
        fun createRoute(animeType: Int): String {
            return this.route.replace(
                oldValue = "{anime_type}",
                newValue = animeType.toString()
            )
        }
    }

    object Collection : Screen("collection")
    object More : Screen("more")
    object Search : Screen("search")
    object DetailAnimeScreen : Screen("anime/{mal_id}") {
        fun createRoute(animeID: Int): String {
            return this.route.replace(
                oldValue = "{mal_id}",
                newValue = animeID.toString()
            )
        }
    }
}
