package io.shelfy.architecture.data.source.local;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;

public interface LocalDataSource {

    Completable saveMovies(List<Movie> movies);

    Completable saveMovieVideo(int movieId, MovieVideo movieVideo);

    Single<List<Movie>> getMovies();

    Maybe<MovieVideo> getMovieVideo(int movieId);
}
