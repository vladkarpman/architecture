package io.shelfy.architecture.data.datasource.remote.mappers;

import androidx.annotation.NonNull;

import io.shelfy.architecture.ApiConstants;
import io.shelfy.architecture.data.datasource.remote.response.MovieVideoJson;
import io.shelfy.architecture.common.Mapper;
import io.shelfy.architecture.domain.entity.MovieVideo;

public class MovieVideoMapper implements Mapper<MovieVideoJson, MovieVideo> {
    @NonNull
    @Override
    public MovieVideo map(@NonNull MovieVideoJson movieVideoJson) {
        return new MovieVideo(ApiConstants.YOUTUBE_BASE_URL + movieVideoJson.getKey());
    }
}
