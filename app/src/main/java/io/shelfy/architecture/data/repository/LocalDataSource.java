package io.shelfy.architecture.data.repository;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;

public interface LocalDataSource {

    Completable saveMovies(List<Movie> movies);

    Single<List<Movie>> getMovies();

    Maybe<MovieVideo> getMovieVideo(int movieId);
}
