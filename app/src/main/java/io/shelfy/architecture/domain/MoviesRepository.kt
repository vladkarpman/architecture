package io.shelfy.architecture.domain

import io.shelfy.architecture.domain.model.Movie
import io.shelfy.architecture.domain.model.MovieVideo

interface MoviesRepository {

    fun getPopularMovies(callback: RepositoryCallback)

    fun getMoviesStartWith(query: String, callback: RepositoryCallback)

    fun getMovieTrailer(movieId: Int, callback: (MovieVideo?) -> Unit)
}

interface RepositoryCallback {

    fun onSuccess(movies: List<Movie>)

    fun onError(message: String)
}
