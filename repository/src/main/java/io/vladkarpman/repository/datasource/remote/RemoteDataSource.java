package io.vladkarpman.repository.datasource.remote;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vladkarpman.domain.entity.Movie;
import io.vladkarpman.domain.entity.MovieVideo;

public interface RemoteDataSource {

    Single<List<Movie>> getMovies();

    Maybe<MovieVideo> getMovieVideo(int movieId);
}
