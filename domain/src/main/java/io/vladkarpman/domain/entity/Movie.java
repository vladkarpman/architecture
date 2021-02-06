package io.vladkarpman.domain.entity;

public class Movie {

    private final int id;
    private final String title;
    private final String description;
    private final String releaseDate;
    private final String posterUrl;
    private final String backdropUr;

    public Movie(int id, String title, String description, String releaseDate, String posterUrl, String backdropUr) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.backdropUr = backdropUr;
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
