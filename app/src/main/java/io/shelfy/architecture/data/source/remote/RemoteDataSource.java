package io.shelfy.architecture.data.source.remote;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;

public interface RemoteDataSource {

    Single<List<Movie>> getMovies();

    Maybe<MovieVideo> getMovieVideo(int movieId);
}
