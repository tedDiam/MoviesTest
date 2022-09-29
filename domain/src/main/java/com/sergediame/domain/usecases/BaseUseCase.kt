package com.sergediame.domain.usecases

import arrow.core.Either
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

abstract class BaseUseCase<ParamsT, ResultT> {
    suspend fun execute(params: ParamsT): Either<Throwable, ResultT> {
        return try {
            val result = withContext(Dispatchers.IO) { doJob(params) }
            return Either.Right(result)
        } catch (throwable: Throwable) {

             when (throwable) {
                is CancellationException -> print("Cancellation")
                else -> print("Error")
            }

            Either.Left(throwable)
        }
    }



    protected abstract suspend fun doJob(params: ParamsT): ResultT
}

interface UseCaseWithoutParam<out T : Flow<Any>> {
    suspend fun execute(): T
}


interface UseCaseWithParams<out T : Flow<Any>, in Params> {
    suspend fun execute(params: Params): T
}