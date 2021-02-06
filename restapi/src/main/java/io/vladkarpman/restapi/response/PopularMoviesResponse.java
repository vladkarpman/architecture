package io.vladkarpman.restapi.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularMoviesResponse {

    @SerializedName("results")
    List<MovieJson> movies;

    public List<MovieJson> getMovies() {
        return movies;
    }

    public PopularMoviesResponse setMovies(List<MovieJson> movies) {
        this.movies = movies;
        return this;
    }
}
