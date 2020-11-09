package io.shelfy.architecture.data.source.remote;

import io.reactivex.rxjava3.core.Single;
import io.shelfy.architecture.data.source.remote.response.MovieVideosResponse;
import io.shelfy.architecture.data.source.remote.response.PopularMoviesResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {

    @GET("movie/popular")
    Single<PopularMoviesResponse> getPopularMovies(
            @Query("api_key") String apiKey
    );

    @GET("movie/{movieId}/videos")
    Single<MovieVideosResponse> getMovieVideos(
            @Path("movieId") int movieId,
            @Query("api_key") String apiKey
    );
}
