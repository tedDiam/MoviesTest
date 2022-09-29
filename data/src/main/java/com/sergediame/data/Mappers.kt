package com.sergediame.data


import com.sergediame.data.model.MovieDetailsResponse
import com.sergediame.data.model.TrendingMoviesResponse
import com.sergediame.data.model.Result
import com.sergediame.data.model.*


fun TrendingMoviesResponse.toEntity() = com.sergediame.domain.entities.TrendingMovies(
    page = page,
    movies = results.map { it.toEntity() },
    total_pages = total_pages,
    total_results = total_results
)

fun Result.toEntity() = com.sergediame.domain.entities.Movie(
    adult = adult,
    backdrop_path = backdrop_path?:"",
    genre_ids = genre_ids,
    id = id,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path?:"",
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = 0 //vote_count
)


fun MovieDetailsResponse.toEntity() = com.sergediame.domain.entities.MovieDetails(
    adult = adult,
    backdrop_path = backdrop_path,
    belongs_to_collection = "", //belongs_to_collection,
    budget = budget,
    genres = genres.map { it.toEntity() },
    homepage = homepage ?: "",
    id = id,
    imdb_id = imdb_id ?: "",
    original_language = original_language,
    original_title = original_title,
    overview = overview ?: "",
    popularity = popularity,
    poster_path = poster_path ?: "",
    production_companies = production_companies.map { it.toEntity() },
    production_countries = production_countries.map { it.toEntity() },
    release_date = release_date,
    revenue = revenue,
    runtime = runtime ?: 0,
    spoken_languages = spoken_languages.map { it.toEntity() },
    status = status,
    tagline = tagline,
    title = title ?: "",
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)


fun Genre.toEntity() = com.sergediame.domain.entities.Genre(
    id = id,
    name = name
)

fun SpokenLanguage.toEntity() = com.sergediame.domain.entities.SpokenLanguage(
    iso_639_1 = iso_639_1,
    name = name
)

fun ProductionCompany.toEntity() = com.sergediame.domain.entities.ProductionCompany(
    id = id,
    logo_path = logo_path ?: "",
    name = name,
    origin_country = origin_country
)


fun ProductionCountry.toEntity() = com.sergediame.domain.entities.ProductionCountry(
    iso_3166_1 = iso_3166_1,
    name = name
)



