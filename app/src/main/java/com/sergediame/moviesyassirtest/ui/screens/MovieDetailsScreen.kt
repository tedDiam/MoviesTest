package com.sergediame.moviesyassirtest.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sergediame.moviesyassirtest.MovieDetailsUIState
import com.sergediame.moviesyassirtest.MovieDetailsViewModel
import com.sergediame.moviesyassirtest.R
import com.sergediame.moviesyassirtest.extensions.collectAsStateWithLifecycle
import com.sergediame.moviesyassirtest.model.MovieDetailsUiModel
import com.sergediame.moviesyassirtest.ui.composables.MoviesErrorContent
import com.sergediame.moviesyassirtest.ui.theme.Typography


@Composable
fun MovieDetailsRoute(
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MoviesDetailsScreen(uiState)

}
@Composable
internal fun MoviesDetailsScreen(
    uiState: MovieDetailsUIState,
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        MovieDetailsContent(scaffoldState, uiState)
    }

}


@Composable
private fun MovieDetailsContent(
    scaffoldState: ScaffoldState,
    uiState: MovieDetailsUIState,
) {

    if (uiState.isError) {
        val errorMessage = stringResource(R.string.movies_error_refreshing)

        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = errorMessage
            )
        }
    }

    if (uiState.movieDetails != MovieDetailsUiModel.EMPTY){
        MovieDetails(uiState.movieDetails)
    } else {
        MovieDetailsNotAvailableContent(uiState)
    }

}

@Composable
private fun MovieDetailsNotAvailableContent(uiState: MovieDetailsUIState) {
    when {
        uiState.isLoading -> MoviesLoadingPlaceholder()
        uiState.isError -> MoviesErrorContent()
    }
}

@Composable
private fun MovieDetails(
    movie: MovieDetailsUiModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.dimen_small)
        )
    ) {
        AsyncImage(
            model = movie.poster_path,
            contentDescription = stringResource(id = R.string.movie_image_content_description),
        )

        Text(
            text = movie.title,
            style = Typography.h2,
        )

        Text(
            text = movie.release_date,
            style = Typography.subtitle1,
        )

        Text(
            text = movie.overview,
            style = Typography.body1,
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_medium)))

    }
}