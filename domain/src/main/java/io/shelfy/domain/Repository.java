package io.shelfy.domain;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.shelfy.domain.entity.Movie;
import io.shelfy.domain.entity.MovieVideo;

public interface Repository {
    Single<List<Movie>> getPopularMovies();

    Maybe<MovieVideo> getMovieTrailer(int movieId);

    Maybe<Movie> getMovieById(int movieId);
}
