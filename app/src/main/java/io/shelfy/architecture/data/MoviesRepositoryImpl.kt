package io.shelfy.architecture.data

import com.vladk.architecture.api.response.MovieJson
import com.vladk.architecture.api.response.MovieVideoJson
import com.vladk.architecture.domain.MoviesRepository
import com.vladk.architecture.domain.RepositoryCallback
import com.vladk.architecture.domain.model.Movie
import com.vladk.architecture.domain.model.MovieVideo
import com.vladk.architecture.domain.mappers.Mapper

class MoviesRepositoryImpl(
    private val onlineDataSource: MoviesDataSource,
    private val localDataSource: MoviesDataSource,
    private val movieMapper: Mapper<MovieJson, Movie>,
    private val movieVideoMapper: Mapper<MovieVideoJson, MovieVideo>
) : MoviesRepository {

    override fun getPopularMovies(callback: RepositoryCallback) {
        val dataSource = if (localDataSource.hasData()) localDataSource else onlineDataSource
        dataSource.getMovies { result ->
            when (result) {
                is MoviesResult.Success -> {
                    val movies = result.movies.map { movieMapper.map(it) }
                    callback.onSuccess(movies)
                }
                is MoviesResult.Error -> {
                    callback.onError(result.message)
                }
            }
        }
    }

    override fun getMoviesStartWith(query: String, callback: RepositoryCallback) {
        val dataSource = if (localDataSource.hasData()) localDataSource else onlineDataSource
        dataSource.getMoviesStartWith(query) { result ->
            when (result) {
                is MoviesResult.Success -> {
                    val movies = result.movies.map { movieMapper.map(it) }
                    callback.onSuccess(movies)
                }
                is MoviesResult.Error -> {
                    callback.onError(result.message)
                }
            }
        }
    }

    override fun getMovieTrailer(movieId: Int, callback: (MovieVideo?) -> Unit) {
        onlineDataSource.getMovieVideo(movieId) { videoJson ->
            val movie: MovieVideo? = videoJson?.let { movieVideoMapper.map(it) }
            callback(movie)
        }
    }
}
