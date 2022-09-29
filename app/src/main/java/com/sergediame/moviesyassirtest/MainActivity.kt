package com.sergediame.moviesyassirtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.sergediame.moviesyassirtest.extensions.collectWithLifecycle
import com.sergediame.moviesyassirtest.navigation.NavigationFactory
import com.sergediame.moviesyassirtest.navigation.NavigationHost
import com.sergediame.moviesyassirtest.navigation.NavigationManager
import com.sergediame.moviesyassirtest.ui.screens.TrendingMoviesRoute
import com.sergediame.moviesyassirtest.ui.theme.MoviesYassirTestTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationFactories: @JvmSuppressWildcards Set<NavigationFactory>

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesYassirTestTheme {
                val navController = rememberNavController()

                Scaffold {
                    NavigationHost(
                        modifier = Modifier
                            .padding(it),
                        navController = navController,
                        factories = navigationFactories
                    )
                    TrendingMoviesRoute()
                }

                navigationManager
                    .navigationEvent
                    .collectWithLifecycle(
                        key = navController
                    ) {
                        navController.navigate(it.destination, it.configuration)
                    }

            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesYassirTestTheme {

    }
}