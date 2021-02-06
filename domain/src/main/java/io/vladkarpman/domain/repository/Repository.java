package io.vladkarpman.domain.repository;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vladkarpman.domain.entity.Movie;
import io.vladkarpman.domain.entity.MovieVideo;

public interface Repository {
    Single<List<Movie>> getPopularMovies();

    Maybe<MovieVideo> getMovieTrailer(int movieId);

    Maybe<Movie> getMovieById(int movieId);
}
