package io.shelfy.architecture.data.repository;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;

public interface Repository {
    Single<List<Movie>> getPopularMovies();

    Single<List<Movie>> getMoviesStartWith(String query);

    Maybe<MovieVideo> getMovieTrailer(int movieId);
}
