package io.shelfy.architecture.data.source.remote;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.common.Mapper;
import io.shelfy.architecture.data.repository.RemoteDataSource;
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

    @NonNull
    private final Mapper<MovieJson, Movie> movieMapper;

    @NonNull
    private final Mapper<MovieVideoJson, MovieVideo> movieVideoMapper;

    public RemoteDataSourceImpl(@NonNull MoviesApi moviesApi,
                                @NonNull String apiKey,
                                @NonNull Mapper<MovieJson, Movie> movieMapper,
                                @NonNull Mapper<MovieVideoJson, MovieVideo> movieVideoMapper) {
        this.moviesApi = moviesApi;
        this.apiKey = apiKey;
        this.movieMapper = movieMapper;
        this.movieVideoMapper = movieVideoMapper;
    }

    @Override
    public Single<List<Movie>> getMovies() {
        return moviesApi.getPopularMovies(apiKey)
                .flattenAsObservable(PopularMoviesResponse::getMovies)
                .map(movieMapper::map)
                .toList();
    }

    @Override
    public Single<List<Movie>> getMoviesStartWith(String query) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Maybe<MovieVideo> getMovieVideo(int movieId) {
        return moviesApi.getMovieVideos(movieId, apiKey)
                .flattenAsObservable(MovieVideosResponse::getVideos)
                .firstElement()
                .map(movieVideoMapper::map);
    }
}
