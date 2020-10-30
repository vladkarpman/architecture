package io.shelfy.architecture.data.datasource.remote;

import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.common.RestService;
import io.shelfy.architecture.data.datasource.remote.response.MovieVideosResponse;
import io.shelfy.architecture.data.datasource.remote.response.PopularMoviesResponse;
import io.shelfy.architecture.data.repository.RemoteMoviesDataSource;

public class ApiDataSource implements RemoteMoviesDataSource {

    public boolean hasData() {
        return false;
    }

    @Override
    public Single<PopularMoviesResponse> getPopularMovies(String apiKey) {
        return RestService.api.getPopularMovies(apiKey);
    }

    @Override
    public Single<MovieVideosResponse> getMovieVideos(int movieId, String apiKey) {
        return RestService.api.getMovieVideos(movieId, apiKey);
    }
}
