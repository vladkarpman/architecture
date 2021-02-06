package io.vladkarpman.repository;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.internal.functions.Functions;
import io.vladkarpman.domain.repository.Repository;
import io.vladkarpman.domain.entity.Movie;
import io.vladkarpman.domain.entity.MovieVideo;
import io.vladkarpman.repository.datasource.local.LocalDataSource;
import io.vladkarpman.repository.datasource.remote.RemoteDataSource;

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
        return remoteDataSource.getMovies()
                .flatMap(movies -> localDataSource.saveMovies(movies)
                        .toSingleDefault(movies))
                // on error fallback to local source
                .onErrorResumeNext(error -> localDataSource.getMovies());
    }

    @Override
    public Maybe<MovieVideo> getMovieTrailer(int movieId) {
        return remoteDataSource.getMovieVideo(movieId)
                .flatMap(movieVideo -> localDataSource.saveMovieVideo(movieId, movieVideo)
                        .toSingleDefault(movieVideo)
                        .toMaybe())
                // on error fallback to local source
                .onErrorResumeNext(error -> {
                    return localDataSource.getMovieVideo(movieId);
                });
    }

    @Override
    public Maybe<Movie> getMovieById(int movieId) {
        return remoteDataSource.getMovies()
                // on error fallback to local source
                .onErrorResumeNext(error -> localDataSource.getMovies())
                .flattenAsObservable(Functions.identity())
                .filter(movie -> movie.getId() == movieId)
                .firstElement();
    }
}
