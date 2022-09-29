package com.sergediame.domain

import com.sergediame.domain.entities.MovieDetails
import com.sergediame.domain.entities.TrendingMovies
import kotlinx.coroutines.flow.Flow

interface MovieRepository  {
    suspend fun getListOfTrendingMovies(page: Int): Flow<TrendingMovies>
    suspend fun getMovieDetails(movieId:Int): Flow<MovieDetails>
}