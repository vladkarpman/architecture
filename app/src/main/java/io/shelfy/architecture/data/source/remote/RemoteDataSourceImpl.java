package io.shelfy.architecture.data.source.remote;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.shelfy.architecture.Constants;
import io.shelfy.architecture.data.source.remote.response.MovieJson;
import io.shelfy.architecture.data.source.remote.response.MovieVideoJson;
import io.shelfy.architecture.data.source.remote.response.MovieVideosResponse;
import io.shelfy.architecture.data.source.remote.response.PopularMoviesResponse;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;

public class RemoteDataSourceImpl implements RemoteDataSource {

    @NonNull
    private final MoviesApi moviesApi;

    @NonNull
    private final String apiKey;

    public RemoteDataSourceImpl(@NonNull MoviesApi moviesApi, @NonNull String apiKey) {
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
        return new MovieVideo(movieId, Constants.YOUTUBE_BASE_URL + movieVideoJson.getKey());
    }

    @NonNull
    private Movie map(@NonNull MovieJson movieJson) {
        return new Movie(
                movieJson.getId(),
                movieJson.getTitle(),
                movieJson.getOverview(),
                movieJson.getReleaseDate(),
                Constants.POSTER_BASE_URL + movieJson.getPosterPath(),
                Constants.BACKDROP_BASE_URL + movieJson.getBackdropPath()
        );
    }
}
