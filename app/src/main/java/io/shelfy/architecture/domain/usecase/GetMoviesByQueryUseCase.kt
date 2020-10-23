package io.shelfy.architecture.domain.usecase

import com.vladk.architecture.domain.MoviesRepository
import com.vladk.architecture.domain.RepositoryCallback
import com.vladk.architecture.domain.model.Movie

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
