package io.shelfy.architecture.data.datasource.remote.mappers;

import androidx.annotation.NonNull;

import io.shelfy.architecture.ApiConstants;
import io.shelfy.architecture.data.datasource.remote.response.MovieJson;
import io.shelfy.architecture.common.Mapper;
import io.shelfy.architecture.domain.entity.Movie;

public class MovieMapper implements Mapper<MovieJson, Movie> {

    @NonNull
    @Override
    public Movie map(@NonNull MovieJson movieJson) {
        return new Movie(
                movieJson.getId(),
                movieJson.getTitle(),
                movieJson.getOverview(),
                movieJson.getReleaseDate(),
                ApiConstants.POSTER_BASE_URL + movieJson.getPosterPath(),
                ApiConstants.BACKDROP_BASE_URL + movieJson.getBackdropPath()
        );
    }
}
