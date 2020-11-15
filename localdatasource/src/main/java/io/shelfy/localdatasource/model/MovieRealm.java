package io.shelfy.localdatasource.model;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;

public class MovieRealm extends RealmObject {

    @PrimaryKey
    private int id;

    private String title;
    private String description;
    private String releaseDate;
    private String posterUrl;
    private String backdropUr;

    @LinkingObjects("movie")
    private final RealmResults<MovieVideoRealm> movieVideoRealms = null;

    public MovieRealm() {
    }

    public MovieRealm(int id,
                      String title,
                      String description,
                      String releaseDate,
                      String posterUrl,
                      String backdropUr) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.backdropUr = backdropUr;
    }

    public RealmResults<MovieVideoRealm> getMovieVideoRealms() {
        return movieVideoRealms;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getBackdropUrl() {
        return backdropUr;
    }
}
