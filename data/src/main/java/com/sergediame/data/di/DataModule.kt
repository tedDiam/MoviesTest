package com.sergediame.data.di

import com.sergediame.data.ApiService
import com.sergediame.data.RepositoryImpl
import com.sergediame.domain.Repository
import com.sergediame.domain.usecases.GetTrendingMoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [DataModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideGetTrendingMoviesUseCase(
        repository: Repository
    ): GetTrendingMoviesUseCase {
        return GetTrendingMoviesUseCase(repository)
    }

    /*@Provides
    fun provideRefreshRocketsUseCase(
        rocketRepository: RocketRepository
    ): RefreshRocketsUseCase {
        return RefreshRocketsUseCase {
            refreshRockets(rocketRepository)
        }
    }*/

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Binds
        @Singleton
        fun bindRocketRepository(impl: RepositoryImpl): Repository
    }
}
