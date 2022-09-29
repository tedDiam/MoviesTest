package com.sergediame.domain.usecases

import com.sergediame.domain.Repository
import com.sergediame.domain.entities.TrendingMovies
import com.sergediame.domain.resultOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetTrendingMoviesUseCase @Inject constructor(
    private val repository: Repository
) : UseCaseWithParams <Flow<Result<TrendingMovies>>, GetTrendingMoviesUseCase.Params> {


    override suspend fun execute(params: Params): Flow<Result<TrendingMovies>> =
        repository.getListOfTrendingMovies(params.page)
            .map {
                resultOf { it }
            }

    data class Params(val page: Int)

}
