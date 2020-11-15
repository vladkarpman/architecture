package io.shelfy.repository;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.shelfy.domain.Repository;
import io.shelfy.domain.entity.Movie;
import io.shelfy.domain.entity.MovieVideo;

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
        if (true) {
            return remoteDataSource.getMovies()
                    .flatMap(movies -> localDataSource.saveMovies(movies)
                            .toSingleDefault(movies));
        }
        return localDataSource.getMovies();
    }

    @Override
    public Maybe<MovieVideo> getMovieTrailer(int movieId) {
        if (true) {
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
