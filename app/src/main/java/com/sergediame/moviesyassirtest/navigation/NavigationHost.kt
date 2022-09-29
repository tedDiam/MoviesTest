package com.sergediame.moviesyassirtest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sergediame.moviesyassirtest.ui.composables.LogCompositions

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    factories: Set<NavigationFactory>
) {

    LogCompositions("NavigationHost", "MyComposable function")

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
