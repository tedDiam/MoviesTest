package com.sergediame.data.di

import com.sergediame.data.MovieRepositoryImpl
import com.sergediame.domain.MovieRepository
import com.sergediame.domain.usecases.GetMovieDetailsUseCase
import com.sergediame.domain.usecases.GetTrendingMoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DataModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideGetTrendingMoviesUseCase(
        movieRepository: MovieRepository
    ): GetTrendingMoviesUseCase {
        return GetTrendingMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideRefreshMoviesUseCase(
        movieRepository: MovieRepository
    ): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase (movieRepository)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Binds
        @Singleton
        fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository
    }
}
