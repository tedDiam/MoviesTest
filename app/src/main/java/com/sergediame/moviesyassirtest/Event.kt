package com.sergediame.moviesyassirtest
sealed class Event {
    data class ShowMovieDetails(val movieId: Int) : Event()
}
