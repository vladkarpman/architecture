package io.shelfy.architecture.domain.usecase

import com.vladk.architecture.domain.MoviesRepository
import com.vladk.architecture.domain.RepositoryCallback
import com.vladk.architecture.domain.model.Movie

interface GetPopularMoviesUseCase {

    fun getMovies(callback: (GetMoviesResult) -> Unit)
}

class GetPopularMoviesUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetPopularMoviesUseCase {

    override fun getMovies(callback: (GetMoviesResult) -> Unit) {
        moviesRepository.getPopularMovies(object : RepositoryCallback {
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
