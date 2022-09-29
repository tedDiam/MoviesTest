package com.sergediame.moviesyassirtest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable


@Immutable
@Parcelize
data class TrendingMoviesUIState (
    val isLoading: Boolean = false,
    val trendingMovies: TrendingMoviesUiModel = TrendingMoviesUiModel.EMPTY,
    val isError: Boolean = false
    ) : Parcelable {

        sealed class PartialState {
            object Loading : PartialState() // for simplicity: initial loading & refreshing

            data class Fetched(val movies: TrendingMoviesUiModel) : PartialState()

            data class Error(val throwable: Throwable) : PartialState()
        }
    }