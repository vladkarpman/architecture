package io.shelfy.architecture.data.mappers

import com.vladk.architecture.api.ApiConstants
import com.vladk.architecture.api.response.MovieJson
import com.vladk.architecture.domain.model.Movie
import com.vladk.architecture.domain.mappers.Mapper

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
