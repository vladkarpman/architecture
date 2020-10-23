package io.shelfy.architecture.cache

import com.vladk.architecture.api.response.MovieJson

object MoviesStore {

    private val storedMovies = mutableListOf<MovieJson>()

    fun putMovies(movies: List<MovieJson>) {
        storedMovies.clear()
        storedMovies.addAll(movies)
    }

    fun getMovies(): List<MovieJson> {
        return storedMovies
    }
}
