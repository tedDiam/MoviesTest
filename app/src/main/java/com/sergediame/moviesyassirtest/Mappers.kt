package com.sergediame.moviesyassirtest

import com.sergediame.domain.entities.*
import com.sergediame.moviesyassirtest.model.*


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
    poster_path = BuildConfig.IMAGE_BASE_URL.plus(poster_path),
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)

fun MovieDetails.toUiModel() = MovieDetailsUiModel(
    adult = adult,
    backdrop_path = backdrop_path ,
    belongs_to_collection = belongs_to_collection,
    budget = budget,
    genres = genres.map { it.toUiModel() },
    homepage = homepage,
    id = id,
    imdb_id = imdb_id,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    poster_path = BuildConfig.LANDSCAPE_IMAGE_BASE_URL.plus(poster_path),
    production_companies = production_companies.map { it.toUiModel() },
    production_countries = production_countries.map { it.toUiModel() },
    release_date = release_date,
    revenue = revenue,
    runtime = runtime,
    spoken_languages = spoken_languages.map { it.toUiModel() },
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)


fun Genre.toUiModel() = GenreUiModel(
    id = id,
    name = name
)

fun ProductionCompany.toUiModel() = ProductionCompanyUiModel(
    id = id,
    logo_path = logo_path,
    name = name,
    origin_country = origin_country
)

fun ProductionCountry.toUiModel() = ProductionCountryUiModel(
    iso_3166_1 = iso_3166_1,
    name = name
)

fun SpokenLanguage.toUiModel() = SpokenLanguageUiModel(
    iso_639_1 = iso_639_1,
    name = name

)
