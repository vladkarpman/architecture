package io.shelfy.architecture.data.mappers

import com.vladk.architecture.api.ApiConstants
import com.vladk.architecture.api.response.MovieVideoJson
import com.vladk.architecture.domain.model.MovieVideo
import com.vladk.architecture.domain.mappers.Mapper

class MovieVideoMapper : Mapper<MovieVideoJson, MovieVideo> {

    override fun map(from: MovieVideoJson): MovieVideo {
        return with(from) {
            MovieVideo(videoUrl = ApiConstants.YOUTUBE_BASE_URL + key)
        }
    }
}
