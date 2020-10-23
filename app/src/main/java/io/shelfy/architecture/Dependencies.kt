package io.shelfy.architecture

import io.shelfy.architecture.api.ApiDataSource
import io.shelfy.architecture.cache.LocalDataSource
import io.shelfy.architecture.data.MoviesRepositoryImpl
import io.shelfy.architecture.data.MoviesDataSource
import io.shelfy.architecture.data.mappers.MovieMapper
import io.shelfy.architecture.data.mappers.MovieVideoMapper
import io.shelfy.architecture.domain.MoviesRepository
import io.shelfy.architecture.domain.model.Movie
import io.shelfy.architecture.domain.usecase.*
import io.shelfy.architecture.presentation.MovieDetailsViewModelFactory
import io.shelfy.architecture.presentation.MoviesViewModelFactory

object Dependencies {

    // Presentation
    val moviesViewModelFactory by lazy {
        createMoviesViewModelFactory(getPopularMoviesUseCase, getMoviesByQueryUseCase)
    }

    fun createMovieDetailsViewModelFactory(movie: Movie): MovieDetailsViewModelFactory {
        return MovieDetailsViewModelFactory(movie, getMovieTrailerUseCase)
    }

    // Domain
    val getPopularMoviesUseCase by lazy {
        createGetPopularMoviesUseCase(moviesRepository)
    }

    val getMoviesByQueryUseCase by lazy {
        createGetMoviesByQueryUseCase(moviesRepository)
    }

    val getMovieTrailerUseCase by lazy {
        createGetMovieTrailerUseCase(moviesRepository)
    }

    val moviesRepository by lazy {
        createMoviesRepository(onlineDataSource, localDataSource)
    }

    // Data
    val onlineDataSource by lazy {
        createOnlineDataSource()
    }

    val localDataSource by lazy {
        createLocalDataSource()
    }

    // Impls
    private fun createMoviesViewModelFactory(
        getPopularMoviesUseCase: GetPopularMoviesUseCase,
        getMoviesByQueryUseCase: GetMoviesByQueryUseCase
    ): MoviesViewModelFactory {
        return MoviesViewModelFactory(getPopularMoviesUseCase, getMoviesByQueryUseCase)
    }

    private fun createGetPopularMoviesUseCase(moviesRepository: MoviesRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(moviesRepository)
    }

    private fun createGetMoviesByQueryUseCase(moviesRepository: MoviesRepository): GetMoviesByQueryUseCase {
        return GetMoviesByQueryUseCaseImpl(moviesRepository)
    }

    private fun createGetMovieTrailerUseCase(moviesRepository: MoviesRepository): GetMovieTrailerUseCase {
        return GetMovieTrailerUseCaseImpl(moviesRepository)
    }

    private fun createMoviesRepository(
        onlineDataSource: MoviesDataSource,
        localDataSource: MoviesDataSource
    ): MoviesRepository {
        return MoviesRepositoryImpl(
            onlineDataSource = onlineDataSource,
            localDataSource = localDataSource,
            movieMapper = MovieMapper(),
            movieVideoMapper = MovieVideoMapper()
        )
    }

    private fun createOnlineDataSource(): MoviesDataSource {
        return ApiDataSource()
    }

    private fun createLocalDataSource(): MoviesDataSource {
        return LocalDataSource()
    }
}
