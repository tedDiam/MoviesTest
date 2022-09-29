package com.sergediame.moviesyassirtest.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sergediame.moviesyassirtest.ui.screens.TrendingMoviesRoute
import javax.inject.Inject

class MoviesYassirTestNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder) {
        builder.composable(NavigationDestination.TrendingMovies.route) {
            TrendingMoviesRoute()
        }
    }
}
