package io.shelfy.architecture.data.mappers

import io.shelfy.architecture.api.ApiConstants
import io.shelfy.architecture.api.response.MovieVideoJson
import io.shelfy.architecture.domain.model.MovieVideo
import io.shelfy.architecture.domain.mappers.Mapper

class MovieVideoMapper : Mapper<MovieVideoJson, MovieVideo> {

    override fun map(from: MovieVideoJson): MovieVideo {
        return with(from) {
            MovieVideo(videoUrl = ApiConstants.YOUTUBE_BASE_URL + key)
        }
    }
}
