package io.vladkarpman.restapi.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieVideosResponse {
    @SerializedName("results")
    private List<MovieVideoJson> videos;

    public List<MovieVideoJson> getVideos() {
        return videos;
    }

    public MovieVideosResponse setVideos(List<MovieVideoJson> videos) {
        this.videos = videos;
        return this;
    }
}
