package io.shelfy.architecture.data.repository;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.domain.Repository;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;

public class RepositoryImpl implements Repository {

    @NonNull
    private final LocalDataSource localDataSource;

    @NonNull
    private final RemoteDataSource remoteDataSource;

    public RepositoryImpl(@NonNull LocalDataSource localDataSource,
                          @NonNull RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Single<List<Movie>> getPopularMovies() {
        return null;
    }

    @Override
    public Single<List<Movie>> getMoviesStartWith(String query) {
        return null;
    }

    @Override
    public Maybe<MovieVideo> getMovieTrailer(int movieId) {
        return null;
    }

    @Override
    public Maybe<Movie> getMovieById(int movieId) {
        return null;
    }
}
