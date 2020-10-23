package io.shelfy.architecture

import com.vladk.architecture.api.ApiDataSource
import com.vladk.architecture.cache.LocalDataSource
import com.vladk.architecture.data.MoviesRepositoryImpl
import com.vladk.architecture.data.MoviesDataSource
import com.vladk.architecture.data.mappers.MovieMapper
import com.vladk.architecture.data.mappers.MovieVideoMapper
import com.vladk.architecture.domain.MoviesRepository
import com.vladk.architecture.domain.model.Movie
import com.vladk.architecture.domain.usecase.*
import com.vladk.architecture.presentation.MovieDetailsViewModelFactory
import com.vladk.architecture.presentation.MoviesViewModelFactory

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
