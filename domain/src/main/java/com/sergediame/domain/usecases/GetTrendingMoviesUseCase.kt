package com.sergediame.domain.usecases

import com.sergediame.domain.MovieRepository
import com.sergediame.domain.entities.TrendingMovies
import com.sergediame.domain.resultOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetTrendingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCaseWithParams <Flow<Result<TrendingMovies>>, GetTrendingMoviesUseCase.Params> {

    suspend operator fun invoke(params: Params) = execute(params)

    override suspend fun execute(params: Params): Flow<Result<TrendingMovies>> =
        movieRepository.getListOfTrendingMovies(params.page)
            .map {
                resultOf { it }
            }

    data class Params(val page: Int)
}