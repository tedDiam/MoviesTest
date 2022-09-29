package com.sergediame.moviesyassirtest

sealed class Intent {
    data class GetTrendingMovies(val page:Int) : Intent()
    object RefreshTrendingMovies : Intent()
    data class MovieClicked(val movieId:Int):Intent()
}
