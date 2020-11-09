package io.shelfy.architecture.data.repository;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;

public interface Repository {
    Single<List<Movie>> getPopularMovies();

    Maybe<MovieVideo> getMovieTrailer(int movieId);

    Maybe<Movie> getMovieById(int movieId);
}
