package io.shelfy.remotedatasource;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.shelfy.domain.entity.Movie;
import io.shelfy.domain.entity.MovieVideo;
import io.shelfy.remotedatasource.response.MovieJson;
import io.shelfy.remotedatasource.response.MovieVideoJson;
import io.shelfy.remotedatasource.response.MovieVideosResponse;
import io.shelfy.remotedatasource.response.PopularMoviesResponse;
import io.shelfy.repository.RemoteDataSource;

import static io.shelfy.remotedatasource.Constants.BACKDROP_BASE_URL;
import static io.shelfy.remotedatasource.Constants.POSTER_BASE_URL;
import static io.shelfy.remotedatasource.Constants.YOUTUBE_BASE_URL;

class RestApi implements RemoteDataSource {

    @NonNull
    private final MoviesApi moviesApi;

    @NonNull
    private final String apiKey;

    public RestApi(@NonNull MoviesApi moviesApi, @NonNull String apiKey) {
        this.moviesApi = moviesApi;
        this.apiKey = apiKey;
    }

    @Override
    public Single<List<Movie>> getMovies() {
        return moviesApi.getPopularMovies(apiKey)
                .flattenAsObservable(PopularMoviesResponse::getMovies)
                .map(this::map)
                .toList();
    }

    @Override
    public Maybe<MovieVideo> getMovieVideo(int movieId) {
        return moviesApi.getMovieVideos(movieId, apiKey)
                .flattenAsObservable(MovieVideosResponse::getVideos)
                .firstElement()
                .map(movieVideoJson -> map(movieId, movieVideoJson));
    }

    @NonNull
    private MovieVideo map(int movieId, @NonNull MovieVideoJson movieVideoJson) {
        return new MovieVideo(movieId, YOUTUBE_BASE_URL + movieVideoJson.getKey());
    }

    @NonNull
    private Movie map(@NonNull MovieJson movieJson) {
        return new Movie(
                movieJson.getId(),
                movieJson.getTitle(),
                movieJson.getOverview(),
                movieJson.getReleaseDate(),
                POSTER_BASE_URL + movieJson.getPosterPath(),
                BACKDROP_BASE_URL + movieJson.getBackdropPath()
        );
    }
}
