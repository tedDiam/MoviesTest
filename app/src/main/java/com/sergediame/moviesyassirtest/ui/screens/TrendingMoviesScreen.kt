package com.sergediame.moviesyassirtest.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sergediame.moviesyassirtest.*
import com.sergediame.moviesyassirtest.extensions.collectAsStateWithLifecycle
import com.sergediame.moviesyassirtest.extensions.collectWithLifecycle
import kotlinx.coroutines.flow.Flow


@Composable
fun TrendingMoviesRoute(
    viewModel: MainViewModel = hiltViewModel()
) {
    HandleEvents(viewModel.event)

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TrendingMoviesScreen(
        uiState = uiState,
        onRefreshMovies = {
            viewModel.acceptIntent(Intent.RefreshTrendingMovies)
        },
        onMovieClicked = {
            viewModel.acceptIntent(Intent.MovieClicked(it))
        }

    )
}


@Composable
private fun HandleEvents(events: Flow<Event>) {

    events.collectWithLifecycle {
        when (it) {
            is Event.ShowMovieDetails -> {

            }
        }
    }
}

@Composable
internal fun TrendingMoviesScreen(
    uiState: TrendingMoviesUIState,
    onRefreshMovies: () -> Unit,
    onMovieClicked: (Int) -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(uiState.isLoading),
            onRefresh = onRefreshMovies,
            modifier = Modifier.padding(it)
        ) {
            if (uiState.trendingMovies.movies.isNotEmpty()) {
                TrendingMoviesAvailableContent(
                    scaffoldState = scaffoldState,
                    uiState = uiState,
                    onMovieClick = onMovieClicked
                )
            } else {
                TrendingMoviesNotAvailableContent(
                    uiState = uiState
                )
            }
        }
    }
}

@Composable
private fun TrendingMoviesAvailableContent(
    scaffoldState: ScaffoldState,
    uiState: TrendingMoviesUIState,
    onMovieClick: (Int) -> Unit
) {
    if (uiState.isError) {
        val errorMessage = stringResource(R.string.rockets_error_refreshing)

        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = errorMessage
            )
        }
    }

    RocketsListContent(
        movieList = uiState.trendingMovies.movies,
        onMovieClick = { onMovieClick(it) }
    )
}

@Composable
private fun TrendingMoviesNotAvailableContent(uiState: TrendingMoviesUIState) {
    when {
        uiState.isLoading -> MoviesLoadingPlaceholder()
        uiState.isError -> MoviesErrorContent()
    }
}

const val MOVIE_DIVIDER_TEST_TAG = "rocketDividerTestTag"


@Composable
fun RocketsListContent(
    movieList: List<MovieUiModel>,
    onMovieClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.dimen_medium)
            )
    ) {
        itemsIndexed(
            items = movieList,
            key = { _, movie -> movie.id }
        ) { index, item ->
            RocketItem(
                movie = item,
                onMovieClick = { onMovieClick(item.id) }
            )

            if (index < movieList.lastIndex) {
                Divider(
                    modifier = Modifier.testTag(MOVIE_DIVIDER_TEST_TAG)
                )
            }
        }
    }
}

