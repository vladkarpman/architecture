package io.shelfy.localdatasource;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.shelfy.domain.entity.Movie;
import io.shelfy.domain.entity.MovieVideo;
import io.shelfy.localdatasource.model.MovieRealm;
import io.shelfy.localdatasource.model.MovieVideoRealm;
import io.shelfy.repository.LocalDataSource;

class RealmDB implements LocalDataSource {

    @NonNull
    private final RealmConfiguration realmConfiguration;

    public RealmDB(@NonNull RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    @Override
    public Completable saveMovies(List<Movie> movies) {
        return Observable.fromIterable(movies)
                .subscribeOn(Schedulers.io())
                .map(this::map)
                .toList()
                .flatMapCompletable(moviesRealm -> Completable.fromAction(() -> {
                    try (Realm realm = Realm.getInstance(realmConfiguration)) {
                        realm.executeTransaction(db -> db.insertOrUpdate(moviesRealm));
                    }
                }));
    }

    @Override
    public Completable saveMovieVideo(int movieId, MovieVideo movieVideo) {
        return Completable.fromAction(() -> {
            try (Realm realm = Realm.getInstance(realmConfiguration)) {
                realm.executeTransaction(db -> {
                    final MovieRealm movieRealm = db.where(MovieRealm.class)
                            .equalTo("id", movieId)
                            .findFirst();
                    if (movieRealm != null) {
                        db.insertOrUpdate(map(movieRealm, movieVideo));
                    }
                });
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<Movie>> getMovies() {
        return Single.<List<Movie>>create(
                emitter -> {
                    try (Realm realm = Realm.getInstance(realmConfiguration)) {
                        final RealmResults<MovieRealm> movieRealms = realm.where(MovieRealm.class).findAll();
                        final List<Movie> movies = Observable.fromIterable(movieRealms)
                                .map(this::map)
                                .toList()
                                .blockingGet();
                        emitter.onSuccess(movies);
                    } catch (Exception e) {
                        emitter.onError(e);
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Maybe<MovieVideo> getMovieVideo(int movieId) {
        return Maybe.create(emitter -> {
            try (Realm realm = Realm.getInstance(realmConfiguration)) {
                final MovieRealm movieRealm = realm.where(MovieRealm.class)
                        .equalTo("id", movieId)
                        .findFirst();

                if (movieRealm != null) {
                    final RealmResults<MovieVideoRealm> videoRealms = movieRealm.getMovieVideoRealms();
                    if (videoRealms != null) {
                        MovieVideoRealm videoRealm = videoRealms.first(null);
                        if (videoRealm != null) {
                            emitter.onSuccess(map(movieId, videoRealm));
                            return;
                        }
                    }
                }
                emitter.onComplete();
            } catch (Exception error) {
                emitter.onError(error);
            }
        });
    }

    @NonNull
    private MovieVideo map(int movieId, @NonNull MovieVideoRealm movieVideoRealm) {
        return new MovieVideo(movieId, movieVideoRealm.getVideoUrl());
    }

    @NonNull
    private MovieVideoRealm map(MovieRealm movieRealm, @NonNull MovieVideo movieVideo) {
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
