package com.sergediame.data

import com.sergediame.domain.MovieRepository
import com.sergediame.domain.entities.MovieDetails
import com.sergediame.domain.entities.TrendingMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val apiService: ApiService) : MovieRepository {

    override suspend fun getListOfTrendingMovies(page: Int): Flow<TrendingMovies> {
        return flow {
            emit(apiService.getListOfTrendingMovies(page).toEntity())
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails> {
        return flow {
            emit(apiService.getMovieDetails(movieId).toEntity())
        }
    }
}