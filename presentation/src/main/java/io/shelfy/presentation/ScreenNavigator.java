package io.shelfy.presentation;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import io.shelfy.domain.entity.Movie;

public interface ScreenNavigator {

    @UiThread
    void showMovies();

    @UiThread
    void showMovieDetails(@NonNull Movie movie);
}
