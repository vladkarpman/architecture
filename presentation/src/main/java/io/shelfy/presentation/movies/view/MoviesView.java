package io.shelfy.presentation.movies.view;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import java.util.List;

import io.reactivex.Observable;
import io.shelfy.domain.entity.Movie;
import io.shelfy.presentation.common.view.CommonView;

public interface MoviesView extends CommonView {

    @UiThread
    void showProgress();

    @UiThread
    void hideProgress();

    @UiThread
    void showMovies(@NonNull List<Movie> movies);

    @UiThread
    void showError(@NonNull String message);

    Observable<Movie> onMovieClicked();
}
