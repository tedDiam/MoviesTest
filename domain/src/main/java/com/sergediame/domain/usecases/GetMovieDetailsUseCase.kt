package com.sergediame.domain.usecases

import com.sergediame.domain.MovieRepository
import com.sergediame.domain.entities.MovieDetails
import com.sergediame.domain.resultOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCaseWithParams<Flow<Result<MovieDetails>>, GetMovieDetailsUseCase.Params> {

    suspend operator fun invoke(params: Params) = execute(params)

    override suspend fun execute(params: Params): Flow<Result<MovieDetails>> =
        movieRepository.getMovieDetails(params.movieId).map {
            resultOf { it }
        }

    data class Params(val movieId: Int)
}
