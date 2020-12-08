package io.shelfy.presentation.common.screensnavigator;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import io.shelfy.domain.entity.Movie;

public interface ScreensNavigator {

    @UiThread
    void showMovies();

    @UiThread
    void showMovieDetails(@NonNull Movie movie);
}
