package com.sergediame.moviesyassirtest

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.sergediame.domain.usecases.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle,
    movieDetailsUIState: MovieDetailsUIState
) : BaseViewModel<MovieDetailsUIState, MovieDetailsUIState.PartialState, Event, MovieDetailsIntent>(
    savedStateHandle, movieDetailsUIState
) {
    private val movieId: Int = checkNotNull(savedStateHandle[MOVIE_ID_KEY])

    init {
        acceptIntent(MovieDetailsIntent.GetDetails(movieId))
    }

    override fun mapIntents(intent: MovieDetailsIntent): Flow<MovieDetailsUIState.PartialState> =
        when (intent) {
            is MovieDetailsIntent.GetDetails -> getMovieDetails(movieId)
        }

    private fun getMovieDetails(movieId: Int): Flow<MovieDetailsUIState.PartialState> = flow {
        getMovieDetailsUseCase(params = GetMovieDetailsUseCase.Params(movieId))
            .onStart {
                emit(MovieDetailsUIState.PartialState.Loading)
            }.collect { result ->
                result
                    .onSuccess { movieDetails ->
                        emit(
                            MovieDetailsUIState.PartialState
                                .Fetched(movieDetails.toUiModel())
                        )
                    }
                    .onFailure {
                        emit(MovieDetailsUIState.PartialState.Error(it))
                    }
            }
    }

    override fun reduceUiState(
        previousState: MovieDetailsUIState,
        partialState: MovieDetailsUIState.PartialState
    ): MovieDetailsUIState {
        return when (partialState) {
            is MovieDetailsUIState.PartialState.Loading -> {
                previousState.copy(
                    isLoading = true,
                    isError = false
                )
            }
            is MovieDetailsUIState.PartialState.Fetched -> {

                previousState.copy(
                    isLoading = false,
                    movieDetails = partialState.details,
                    isError = false
                )
            }
            is MovieDetailsUIState.PartialState.Error -> {
                previousState.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

}