package com.sergediame.moviesyassirtest

import com.sergediame.domain.entities.Movie
import com.sergediame.domain.entities.TrendingMovies


fun TrendingMovies.toUiModel() = TrendingMoviesUiModel(
    page = page,
    movies = movies.map { it.toUiModel() },
    total_pages = total_pages,
    total_results = total_results
)

fun Movie.toUiModel() = MovieUiModel(
    adult = adult,
    backdrop_path = backdrop_path,
    genre_ids = genre_ids,
    id = id,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)