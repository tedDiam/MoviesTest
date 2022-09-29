package com.sergediame.moviesyassirtest

import androidx.lifecycle.SavedStateHandle
import com.sergediame.domain.usecases.GetTrendingMoviesUseCase
import com.sergediame.moviesyassirtest.navigation.NavigationManager
import com.sergediame.moviesyassirtest.navigation.TrendingMoviesDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val navigationManager: NavigationManager,
    savedStateHandle: SavedStateHandle,
    moviesUIState: MoviesUIState
) : BaseViewModel<MoviesUIState, MoviesUIState.PartialState, Event, TrendingMoviesIntent>(
    savedStateHandle, moviesUIState
) {

    init {
        acceptIntent(TrendingMoviesIntent.GetMovies(page = 1))
    }

    override fun mapIntents(intent: TrendingMoviesIntent): Flow<MoviesUIState.PartialState> =
        when (intent) {
            is TrendingMoviesIntent.GetMovies -> getTrendingMovies(page = 1)
            is TrendingMoviesIntent.MovieClicked -> onMovieClicked(intent.movieId)
            TrendingMoviesIntent.RefreshMovies -> getTrendingMovies(page = 1)
        }

    private fun onMovieClicked(movieId: Int): Flow<MoviesUIState.PartialState> {

        val trendingMoviesDirections = TrendingMoviesDirections(movieId).movieDetails

        navigationManager.navigate(trendingMoviesDirections)
        return emptyFlow()
    }


    private fun getTrendingMovies(page: Int): Flow<MoviesUIState.PartialState> = flow {

        getTrendingMoviesUseCase(params = GetTrendingMoviesUseCase.Params(page))
            .onStart {
                emit(MoviesUIState.PartialState.Loading)
            }.collect { result ->
                result
                    .onSuccess { trendingMovies ->
                        emit(
                            MoviesUIState.PartialState
                                .Fetched(trendingMovies.toUiModel())
                        )
                    }
                    .onFailure {
                        emit(MoviesUIState.PartialState.Error(it))
                    }
            }
    }


    override fun reduceUiState(
        previousState: MoviesUIState,
        partialState: MoviesUIState.PartialState
    ): MoviesUIState {
        return when (partialState) {
            is MoviesUIState.PartialState.Loading -> {
                previousState.copy(
                    isLoading = true,
                    isError = false
                )
            }
            is MoviesUIState.PartialState.Fetched -> {
                previousState.copy(
                    isLoading = false,
                    trendingMovies = partialState.movies,
                    isError = false
                )
            }
            is MoviesUIState.PartialState.Error -> {
                previousState.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

}