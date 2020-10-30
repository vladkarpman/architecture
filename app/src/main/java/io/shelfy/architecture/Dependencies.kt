package io.shelfy.architecture

import io.shelfy.architecture.data.datasource.remote.ApiDataSource
import io.shelfy.architecture.data.datasource.local.LocalDataSourceImpl
import io.shelfy.architecture.data.repository.MoviesRepositoryImpl
import io.shelfy.architecture.data.repository.RemoteMoviesDataSource
import io.shelfy.architecture.data.datasource.remote.mappers.MovieMapper
import io.shelfy.architecture.data.datasource.remote.mappers.MovieVideoMapper
import io.shelfy.architecture.data.repository.MoviesRepository
import io.shelfy.architecture.domain.usecase.*
import io.shelfy.architecture.presentation.MovieDetailsViewModelFactory
import io.shelfy.architecture.presentation.viewmodel.ViewModelFactory

object Dependencies {

    // Presentation
    val moviesViewModelFactory by lazy {
        createMoviesViewModelFactory(getPopularMoviesUseCase, getMoviesByQueryUseCase)
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
    ): ViewModelFactory {
        return ViewModelFactory(
            getPopularMoviesUseCase,
            getMoviesByQueryUseCase
        )
    }

    private fun createGetPopularMoviesUseCase(moviesRepository: io.shelfy.architecture.data.repository.MoviesRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(moviesRepository)
    }

    private fun createGetMoviesByQueryUseCase(moviesRepository: io.shelfy.architecture.data.repository.MoviesRepository): GetMoviesByQueryUseCase {
        return GetMoviesByQueryUseCaseImpl(moviesRepository)
    }

    private fun createGetMovieTrailerUseCase(moviesRepository: io.shelfy.architecture.data.repository.MoviesRepository): GetMovieTrailerUseCase {
        return GetMovieTrailerUseCaseImpl(moviesRepository)
    }

    private fun createMoviesRepository(
        onlineDataSource: RemoteMoviesDataSource,
        localDataSource: RemoteMoviesDataSource
    ): io.shelfy.architecture.data.repository.MoviesRepository {
        return MoviesRepositoryImpl(
            onlineDataSource = onlineDataSource,
            localDataSource = localDataSource,
            movieMapper = MovieMapper(),
            movieVideoMapper = MovieVideoMapper()
        )
    }

    private fun createOnlineDataSource(): RemoteMoviesDataSource {
        return ApiDataSource()
    }

    private fun createLocalDataSource(): RemoteMoviesDataSource {
        return LocalDataSourceImpl()
    }
}
