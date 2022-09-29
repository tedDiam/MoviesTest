package com.sergediame.moviesyassirtest.model

import android.os.Parcelable
import kotlinx.android.parcel.RawValue
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieDetailsUiModel(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: @RawValue Any,
    val budget: Int,
    val genres: List<GenreUiModel>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompanyUiModel>,
    val production_countries: List<ProductionCountryUiModel>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguageUiModel>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable{
    companion object {
        val EMPTY = MovieDetailsUiModel(
            adult = false,
            backdrop_path = "",
            belongs_to_collection = Any(),
            budget = 0,
            genres = emptyList(),
            homepage = "",
            id = 0,
            imdb_id = "",
            original_language = "",
            original_title = "",
            overview = "",
            popularity = 0.0,
            poster_path = "",
            production_companies = emptyList(),
            production_countries = emptyList(),
            release_date = "",
            revenue = 0,
            runtime = 0,
            spoken_languages = emptyList(),
            status = "",
            tagline = "",
            title = "",
            video = false,
            vote_average = 0.0,
            vote_count = 0
        )
    }
}

@Parcelize
data class GenreUiModel(
    val id: Int,
    val name: String
): Parcelable

@Parcelize
data class ProductionCompanyUiModel(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
): Parcelable


@Parcelize
data class ProductionCountryUiModel(
    val iso_3166_1: String,
    val name: String
): Parcelable

@Parcelize
data class SpokenLanguageUiModel(
    val iso_639_1: String,
    val name: String
): Parcelable