package com.sergediame.moviesyassirtest



    sealed class TrendingMoviesIntent {
        data class GetMovies(val page:Int) : TrendingMoviesIntent()
        object RefreshMovies : TrendingMoviesIntent()
        data class MovieClicked(val movieId:Int):TrendingMoviesIntent()
    }

    sealed class MovieDetailsIntent {
        data class GetDetails(val movieId:Int): MovieDetailsIntent()
    }

