package io.shelfy.realmdb;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.shelfy.domain.entity.Movie;
import io.shelfy.domain.entity.MovieVideo;
import io.shelfy.realmdb.model.MovieRealm;
import io.shelfy.realmdb.model.MovieVideoRealm;
import io.shelfy.repository.datasource.local.LocalDataSource;
import io.shelfy.utils.Mapper;

class RealmDB extends BaseRealmDB implements LocalDataSource {

    public RealmDB(@NonNull RealmConfiguration realmConfiguration) {
        super(realmConfiguration);
    }

    @Override
    public Completable saveMovies(List<Movie> movies) {
        return update(movies, (Mapper<Movie, MovieRealm>) this::map);
    }

    @Override
    public Completable saveMovieVideo(int movieId, MovieVideo movieVideo) {
        return execute(realm -> {
            final MovieRealm movieRealm = realm.where(MovieRealm.class)
                    .equalTo("id", movieId)
                    .findFirst();
            if (movieRealm != null) {
                realm.insertOrUpdate(map(movieRealm, movieVideo));
            }
        });
    }

    @Override
    public Single<List<Movie>> getMovies() {
        return read(MovieRealm.class, this::map);
    }

    @Override
    public Maybe<MovieVideo> getMovieVideo(int movieId) {
        return read(
                realm -> {
                    final MovieRealm movieRealm = realm.where(MovieRealm.class)
                            .equalTo("id", movieId)
                            .findFirst();

                    if (movieRealm == null) {
                        return new AtomicReference<MovieVideo>();
                    }

                    final RealmResults<MovieVideoRealm> videoRealms = movieRealm.getMovieVideoRealms();
                    if (videoRealms != null) {
                        MovieVideoRealm videoRealm = videoRealms.first(null);
                        if (videoRealm != null) {
                            return new AtomicReference<>(map(movieId, videoRealm));
                        }
                    }
                    return new AtomicReference<MovieVideo>();
                })
                .filter(optional -> optional.get() != null)
                .map(AtomicReference::get);
    }

    @NonNull
    private MovieVideo map(int movieId, @NonNull MovieVideoRealm movieVideoRealm) {
        return new MovieVideo(movieId, movieVideoRealm.getVideoUrl());
    }

    @NonNull
    private MovieVideoRealm map(@NonNull MovieRealm movieRealm, @NonNull MovieVideo movieVideo) {
        return new MovieVideoRealm(movieRealm, movieVideo.getVideoUrl());
    }

    @NonNull
    private MovieRealm map(@NonNull Movie movie) {
        return new MovieRealm(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseDate(),
                movie.getPosterUrl(),
                movie.getBackdropUrl()
        );
    }

    @NonNull
    private Movie map(@NonNull MovieRealm movieRealm) {
        return new Movie(
                movieRealm.getId(),
                movieRealm.getTitle(),
                movieRealm.getDescription(),
                movieRealm.getReleaseDate(),
                movieRealm.getPosterUrl(),
                movieRealm.getBackdropUrl()
        );
    }
}
