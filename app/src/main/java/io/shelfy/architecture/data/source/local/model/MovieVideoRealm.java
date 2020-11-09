package io.shelfy.architecture.data.source.local.model;

import io.realm.RealmObject;
import io.shelfy.architecture.domain.entity.Movie;

public class MovieVideoRealm extends RealmObject {

    private String videoUrl;

    private MovieRealm movie;

    public MovieVideoRealm() {
    }

    public MovieVideoRealm(MovieRealm movie, String videoUrl) {
        this.movie = movie;
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
