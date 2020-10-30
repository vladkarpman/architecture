package io.shelfy.architecture.data.repository;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;

public interface RemoteDataSource {

    Single<List<Movie>> getMovies();

    Single<List<Movie>> getMoviesStartWith(String query);

    Maybe<MovieVideo> getMovieVideo(int movieId);
}
