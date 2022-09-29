package com.sergediame.moviesyassirtest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrendingMoviesUiModel(
    val page: Int,
    val movies: List<MovieUiModel>,
    val total_pages: Int,
    val total_results: Int
): Parcelable{
    companion object {
        val EMPTY = TrendingMoviesUiModel(
            page = 0,
            movies= emptyList(),
            total_pages = 0,
            total_results = 0
        )
    }
}


@Parcelize
data class MovieUiModel(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
): Parcelable
