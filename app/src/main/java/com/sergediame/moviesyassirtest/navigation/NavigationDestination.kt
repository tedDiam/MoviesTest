package com.sergediame.moviesyassirtest.navigation

sealed class NavigationDestination(
    val route: String
) {
    object TrendingMovies : NavigationDestination("trendingMoviesDestination")
    object MovieDetails : NavigationDestination("movieDetailsDestination")
}
