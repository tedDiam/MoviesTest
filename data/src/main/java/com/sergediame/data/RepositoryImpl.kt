package com.sergediame.data


import android.util.Log
import com.sergediame.domain.Repository
import com.sergediame.domain.entities.MovieDetails
import com.sergediame.domain.entities.TrendingMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(private val apiService: ApiService,): Repository {

    override suspend fun getListOfTrendingMovies(page: Int): Flow<TrendingMovies> {
        Log.d("CALLED", "Repo called")
        return apiService.getListOfTrendingMovies(page).map {
            it.toEntity()
        }
    }


    override suspend fun getMovieDetails(movieId:Int): Flow<MovieDetails> {
        return apiService.getMovieDetails(movieId).map {
            it.toEntity()
        }
    }
}