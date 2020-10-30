package io.shelfy.architecture.data.datasource.local;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.data.repository.LocalMoviesDataSource;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;

public class LocalDataSourceImpl implements LocalMoviesDataSource {

    @Override
    public Completable saveMovies(List<Movie> movies) {
        return null;
    }

    @Override
    public Single<List<Movie>> getMovies() {
        return null;
    }

    @Override
    public Maybe<MovieVideo> getMovieVideo(int movieId) {
        return null;
    }
}
