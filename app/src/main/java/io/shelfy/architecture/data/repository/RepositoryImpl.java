package io.shelfy.architecture.data.repository;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.domain.MoviesRepository;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;

public class RepositoryImpl implements MoviesRepository {

    @NonNull
    private final LocalMoviesDataSource localDataSource;

    @NonNull
    private final RemoteMoviesDataSource remoteDataSource;

    public RepositoryImpl(@NonNull LocalMoviesDataSource localDataSource,
                          @NonNull RemoteMoviesDataSource remoteDataSource) {
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
}
