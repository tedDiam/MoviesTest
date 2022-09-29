package com.sergediame.moviesyassirtest.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sergediame.moviesyassirtest.MOVIE_ID_KEY
import com.sergediame.moviesyassirtest.ui.screens.MovieDetailsRoute
import com.sergediame.moviesyassirtest.ui.screens.TrendingMoviesRoute
import javax.inject.Inject

class MoviesYassirTestNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder) {
        builder.composable(NavigationDestination.TrendingMovies.route) {
            TrendingMoviesRoute()
        }

        builder.composable(
            route = NavigationDestination.MovieDetails
                .route.plus("/{$MOVIE_ID_KEY}"),
            arguments = listOf(navArgument(MOVIE_ID_KEY) {
                type = NavType.IntType; defaultValue = 0
            })
        ) {
            MovieDetailsRoute()
        }
    }
}
