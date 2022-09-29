package com.sergediame.data

import com.sergediame.data.model.MovieDetailsResponse
import com.sergediame.data.model.TrendingMoviesResponse
import com.sergediame.domain.entities.MovieDetails
import kotlinx.coroutines.flow.Flow

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(GET_LIST_OF_TRENDING_MOVIES)
    suspend fun getListOfTrendingMovies( @Query("page") page:Int, @Query("api_key") api_key: String = BuildConfig.API_KEY,): Flow<TrendingMoviesResponse>

    @GET(GET_DETAILS_OF_A_MOVIE)
    suspend fun getMovieDetails(
        @Query("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
    ): Flow<MovieDetailsResponse>

    companion object {
        const val GET_LIST_OF_TRENDING_MOVIES = "discover/movie"
        const val GET_DETAILS_OF_A_MOVIE = "support/category-list"
    }
}