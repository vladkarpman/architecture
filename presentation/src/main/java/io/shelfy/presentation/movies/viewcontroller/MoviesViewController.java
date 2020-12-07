package io.shelfy.presentation.movies.viewcontroller;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.shelfy.domain.entity.Movie;
import io.shelfy.presentation.ScreenNavigator;
import io.shelfy.presentation.common.viewcontroller.BaseViewController;
import io.shelfy.presentation.movies.view.MoviesView;
import io.shelfy.presentation.movies.viewmodel.MoviesViewModel;

public class MoviesViewController extends BaseViewController<MoviesView, MoviesViewModel> {

    @NonNull
    private final MoviesView moviesView;

    @NonNull
    private final MoviesViewModel viewModel;

    @NonNull
    private final ScreenNavigator screenNavigator;

    public MoviesViewController(
            @NonNull LifecycleOwner lifecycleOwner,
            @NonNull MoviesView view,
            @NonNull MoviesViewModel viewModel,
            @NonNull ScreenNavigator screenNavigator) {
        super(lifecycleOwner, view, viewModel);
        this.moviesView = view;
        this.viewModel = viewModel;
        this.screenNavigator = screenNavigator;

        moviesView.showProgress();

        viewModel.getMovies().observe(lifecycleOwner, movies -> {
            moviesView.hideProgress();
            moviesView.showMovies(movies);
        });

        viewModel.getErrorMessages().observe(lifecycleOwner, moviesView::showError);

        onDestroyDisposables.add(moviesView.onMovieClicked()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showDetailsFragment));
    }

    private void showDetailsFragment(Movie movie) {
        screenNavigator.showMovieDetails(movie);
    }
}
