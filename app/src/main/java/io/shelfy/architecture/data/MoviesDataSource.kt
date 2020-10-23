package io.shelfy.architecture.data

import io.shelfy.architecture.api.response.MovieJson
import io.shelfy.architecture.api.response.MovieVideoJson

interface MoviesDataSource {

    fun hasData(): Boolean

    fun getMovies(callback: (MoviesResult) -> Unit)

    fun getMoviesStartWith(query: String, callback: (MoviesResult) -> Unit)

    fun getMovieVideo(movieId: Int, callback: (MovieVideoJson?) -> Unit)
}

sealed class MoviesResult {

    data class Success(val movies: List<MovieJson>) : MoviesResult()

    data class Error(val message: String) : MoviesResult()
}
