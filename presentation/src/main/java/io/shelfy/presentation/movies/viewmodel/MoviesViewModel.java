package io.shelfy.presentation.movies.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.shelfy.domain.entity.Movie;
import io.shelfy.presentation.common.view.CommonView;
import io.shelfy.presentation.common.viewmodel.CommonViewModel;

public interface MoviesViewModel extends CommonViewModel {
    LiveData<String> getErrorMessages();

    LiveData<List<Movie>> getMovies();

    void loadPopularMovies();

    void search(@NonNull String query);
}
