package io.shelfy.architecture.domain.entity;

public class MovieVideo {
    private final String videoUrl;
    private final int movieId;

    public MovieVideo(int movieId, String videoUrl) {
        this.videoUrl = videoUrl;
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
