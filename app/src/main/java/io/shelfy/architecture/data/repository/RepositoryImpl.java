package io.shelfy.architecture.data.repository;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.shelfy.architecture.data.source.local.LocalDataSource;
import io.shelfy.architecture.data.source.remote.RemoteDataSource;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;
import io.shelfy.architecture.util.NetworkConnectivityHelper;

public class RepositoryImpl implements Repository {

    @NonNull
    private final LocalDataSource localDataSource;

    @NonNull
    private final RemoteDataSource remoteDataSource;

    @NonNull
    private final NetworkConnectivityHelper networkConnectivityHelper;

    public RepositoryImpl(@NonNull LocalDataSource localDataSource,
                          @NonNull RemoteDataSource remoteDataSource,
                          @NonNull NetworkConnectivityHelper networkConnectivityHelper) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.networkConnectivityHelper = networkConnectivityHelper;
    }

    @Override
    public Single<List<Movie>> getPopularMovies() {
        if (networkConnectivityHelper.isConnected()) {
            return remoteDataSource.getMovies()
                    .flatMap(movies -> localDataSource.saveMovies(movies)
                            .toSingleDefault(movies));
        }
        return localDataSource.getMovies();
    }

    @Override
    public Maybe<MovieVideo> getMovieTrailer(int movieId) {
        if (networkConnectivityHelper.isConnected()) {
            return remoteDataSource.getMovieVideo(movieId)
                    .flatMap(movieVideo -> localDataSource.saveMovieVideo(movieId, movieVideo)
                            .toSingleDefault(movieVideo)
                            .toMaybe());
        }
        return localDataSource.getMovieVideo(movieId);
    }

    @Override
    public Maybe<Movie> getMovieById(int movieId) {
        return null;
//        return remoteDataSource.getMovies();
    }
}
