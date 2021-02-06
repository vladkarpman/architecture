package io.vladkarpman.presentation.movies.viewcontroller;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.vladkarpman.domain.entity.Movie;
import io.vladkarpman.presentation.common.screensnavigator.ScreensNavigator;
import io.vladkarpman.presentation.common.viewcontroller.BaseViewController;
import io.vladkarpman.presentation.movies.view.MoviesView;
import io.vladkarpman.presentation.movies.viewmodel.MoviesViewModel;

public class MoviesViewController extends BaseViewController<MoviesView, MoviesViewModel> {

    @NonNull
    private final ScreensNavigator screensNavigator;

    public MoviesViewController(
            @NonNull MoviesView view,
            @NonNull MoviesViewModel viewModel,
            @NonNull ScreensNavigator screensNavigator) {
        super(view, viewModel);
        this.screensNavigator = screensNavigator;
    }

    @Override
    public void attachToLifecycle(@NonNull LifecycleOwner lifecycleOwner) {
        super.attachToLifecycle(lifecycleOwner);

        viewModel.getMovies().observe(lifecycleOwner, movies -> {
            view.hideProgress();
            view.showMovies(movies);
        });

        viewModel.getErrorMessages().observe(lifecycleOwner, view::showError);
    }

    @Override
    public void onStart() {
        super.onStart();
        view.showProgress();

        onDestroyDisposables.add(view.onMovieClicked()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showDetailsFragment));
    }

    private void showDetailsFragment(Movie movie) {
        screensNavigator.showMovieDetails(movie);
    }
}
