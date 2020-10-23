package io.shelfy.architecture.domain.usecase

import io.shelfy.architecture.domain.MoviesRepository
import io.shelfy.architecture.domain.RepositoryCallback
import io.shelfy.architecture.domain.model.Movie

interface GetMoviesByQueryUseCase {

    fun getMovies(query: String, callback: (GetMoviesResult) -> Unit)
}

class GetMoviesByQueryUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetMoviesByQueryUseCase {

    override fun getMovies(query: String, callback: (GetMoviesResult) -> Unit) {
        moviesRepository.getMoviesStartWith(query, object : RepositoryCallback {
            override fun onSuccess(movies: List<Movie>) {
                val successResult = GetMoviesResult.Success(movies)
                callback(successResult)
            }

            override fun onError(message: String) {
                val errorResult = GetMoviesResult.Error(message)
                callback(errorResult)
            }
        })
    }
}
