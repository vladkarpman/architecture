package io.shelfy.architecture.domain.usecase

import io.shelfy.architecture.domain.MoviesRepository
import io.shelfy.architecture.domain.model.MovieVideo

interface GetMovieTrailerUseCase {

    fun getTrailer(movieId: Int, callback: (GetTrailerResult) -> Unit)
}

sealed class GetTrailerResult {

    data class Success(val movieVideo: MovieVideo) : GetTrailerResult()

    data class Error(val message: String) : GetTrailerResult()
}

class GetMovieTrailerUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetMovieTrailerUseCase {

    override fun getTrailer(movieId: Int, callback: (GetTrailerResult) -> Unit) {
        moviesRepository.getMovieTrailer(movieId) { movieVideo ->
            val result = if (movieVideo == null) {
                GetTrailerResult.Error("trailer not found")
            } else {
                GetTrailerResult.Success(movieVideo)
            }
            callback(result)
        }
    }
}
