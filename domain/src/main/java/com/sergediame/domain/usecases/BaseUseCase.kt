package com.sergediame.domain.usecases

import kotlinx.coroutines.flow.Flow

interface UseCaseWithoutParam<out T : Flow<Any>> {
    suspend fun execute(): T
}


interface UseCaseWithParams<out T : Flow<Any>, in Params> {
    suspend fun execute(params: Params): T
}