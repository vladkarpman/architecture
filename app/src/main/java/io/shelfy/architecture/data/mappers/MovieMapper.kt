package io.shelfy.architecture.data.mappers

import io.shelfy.architecture.api.ApiConstants
import io.shelfy.architecture.api.response.MovieJson
import io.shelfy.architecture.domain.model.Movie
import io.shelfy.architecture.domain.mappers.Mapper

class MovieMapper : Mapper<MovieJson, Movie> {

    override fun map(from: MovieJson): Movie {

        return with(from) {
            Movie(
                id = id,
                title = title,
                description = overview,
                releaseDate = releaseDate,
                posterUrl = ApiConstants.POSTER_BASE_URL + posterPath,
                backdropUrl = ApiConstants.BACKDROP_BASE_URL + backdropPath
            )
        }
    }
}
