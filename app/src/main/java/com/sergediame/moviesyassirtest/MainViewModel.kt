package com.sergediame.moviesyassirtest

//import com.sergediame.domain.usecases.GetMovieDetailsUseCase
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.sergediame.domain.usecases.GetTrendingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    //private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle,
    trendingMoviesUIState: TrendingMoviesUIState
) : BaseViewModel<TrendingMoviesUIState, TrendingMoviesUIState.PartialState, Event, Intent>(
    savedStateHandle, trendingMoviesUIState
) {

    init {
        acceptIntent(Intent.GetTrendingMovies(page = 1))
    }
    override fun mapIntents(intent: Intent): Flow<TrendingMoviesUIState.PartialState> = when (intent) {
        is Intent.GetTrendingMovies -> getTrendingMovies(intent.page)
        is Intent.MovieClicked ->  onMovieClicked(intent.movieId)
        is Intent.RefreshTrendingMovies -> getTrendingMovies(page = 1)
    }

    private fun onMovieClicked(movieId: Int): Flow<TrendingMoviesUIState.PartialState> {
        publishEvent(Event.ShowMovieDetails(movieId ))
        return emptyFlow()
    }


    private fun getTrendingMovies(page:Int): Flow<TrendingMoviesUIState.PartialState> = flow {
        getTrendingMoviesUseCase.execute(GetTrendingMoviesUseCase.Params(page))
            .onStart {
                emit(TrendingMoviesUIState.PartialState.Loading)
            }.collect { result ->
                result
                    .onSuccess { trendingMovies ->
                        emit(TrendingMoviesUIState.PartialState.Fetched(trendingMovies.toUiModel()))
                    }
                    .onFailure {
                        emit(TrendingMoviesUIState.PartialState.Error(it))
                    }
            }
    }



    override fun reduceUiState(
        previousState: TrendingMoviesUIState,
        partialState: TrendingMoviesUIState.PartialState
    ): TrendingMoviesUIState = when (partialState) {
        is TrendingMoviesUIState.PartialState.Loading -> {

            previousState.copy(
                isLoading = true,
                isError = false
            )
        }
        is TrendingMoviesUIState.PartialState.Fetched -> {
            previousState.copy(
                isLoading = false,
                trendingMovies = partialState.movies,
                isError = false
            )
        }
        is TrendingMoviesUIState.PartialState.Error -> {
            previousState.copy(
                isLoading = false,
                isError = true
            )
        }
    }

}