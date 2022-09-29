package com.sergediame.moviesyassirtest.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.sergediame.moviesyassirtest.MOVIE_ID_KEY


class TrendingMoviesDirections constructor(val argumentValue: Any? = null) {

    val movieDetails = object : NavigationCommand {
        override val destination: String =
            NavigationDestination.MovieDetails.route.plus("/$argumentValue")
        override val configuration: NavOptions =
            NavOptions.Builder().setPopUpTo(
                route = NavigationDestination.TrendingMovies.route,
                inclusive = true,
                saveState = true
            ).build()
        override val arguments: List<NamedNavArgument> =
            listOf(navArgument(MOVIE_ID_KEY) { type = NavType.IntType; nullable = false })
    }

}