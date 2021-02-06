package io.vladkarpman.presentation.common.screensnavigator;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import io.vladkarpman.domain.entity.Movie;

public interface ScreensNavigator {

    @UiThread
    void showMovies();

    @UiThread
    void showMovieDetails(@NonNull Movie movie);
}
