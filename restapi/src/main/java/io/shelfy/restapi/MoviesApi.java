package io.shelfy.restapi;

import io.reactivex.Single;
import io.shelfy.restapi.response.MovieVideosResponse;
import io.shelfy.restapi.response.PopularMoviesResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface MoviesApi {

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
