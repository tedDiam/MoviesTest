package com.sergediame.moviesyassirtest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    factories: Set<NavigationFactory>
) {

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.TrendingMovies.route,
        modifier = modifier,
    ) {
        factories.forEach {
            it.create(this)
        }
    }
}
