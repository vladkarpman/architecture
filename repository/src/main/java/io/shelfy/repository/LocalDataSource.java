package io.shelfy.repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.shelfy.domain.entity.Movie;
import io.shelfy.domain.entity.MovieVideo;

public interface LocalDataSource {

    Completable saveMovies(List<Movie> movies);

    Completable saveMovieVideo(int movieId, MovieVideo movieVideo);

    Single<List<Movie>> getMovies();

    Maybe<MovieVideo> getMovieVideo(int movieId);
}
