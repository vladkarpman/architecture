package io.vladkarpman.presentation.movies.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.vladkarpman.domain.entity.Movie;
import io.vladkarpman.presentation.common.viewmodel.CommonViewModel;

public interface MoviesViewModel extends CommonViewModel {
    LiveData<String> getErrorMessages();

    LiveData<List<Movie>> getMovies();

    void loadPopularMovies();

    void search(@NonNull String query);
}
