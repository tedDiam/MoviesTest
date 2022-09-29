package com.sergediame.moviesyassirtest.di

import com.sergediame.moviesyassirtest.MovieDetailsUIState
import com.sergediame.moviesyassirtest.MoviesUIState
import com.sergediame.moviesyassirtest.navigation.MoviesYassirTestNavigationFactory
import com.sergediame.moviesyassirtest.navigation.NavigationFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideInitialMoviesUiState(): MoviesUIState = MoviesUIState()

    @Provides
    fun provideInitialMovieDetailsUiState(): MovieDetailsUIState = MovieDetailsUIState()
}


@Module
@InstallIn(SingletonComponent::class)
interface SingletonModule {

    @Singleton
    @Binds
    @IntoSet
    fun bindMoviesNavigationFactory(factory: MoviesYassirTestNavigationFactory): NavigationFactory
}
