package com.sergediame.moviesyassirtest

import android.os.Parcelable
import com.sergediame.domain.entities.MovieDetails
import com.sergediame.moviesyassirtest.model.MovieDetailsUiModel
import com.sergediame.moviesyassirtest.model.TrendingMoviesUiModel
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable


@Immutable
@Parcelize
data class MovieDetailsUIState(
    val isLoading: Boolean = false,
    val movieDetails: MovieDetailsUiModel = MovieDetailsUiModel.EMPTY,
    val isError: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState() // for simplicity: initial loading & refreshing

        data class Fetched(val details: MovieDetailsUiModel) : PartialState()

        data class Error(val throwable: Throwable) : PartialState()
    }
}