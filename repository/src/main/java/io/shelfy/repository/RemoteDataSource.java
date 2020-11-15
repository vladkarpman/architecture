package io.shelfy.repository;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.shelfy.domain.entity.Movie;
import io.shelfy.domain.entity.MovieVideo;

public interface RemoteDataSource {

    Single<List<Movie>> getMovies();

    Maybe<MovieVideo> getMovieVideo(int movieId);
}
