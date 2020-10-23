package io.shelfy.architecture.domain.usecase

import io.shelfy.architecture.domain.model.Movie

sealed class GetMoviesResult {

    data class Success(val movies: List<Movie>) : GetMoviesResult()

    data class Error(val message: String) : GetMoviesResult()
}
