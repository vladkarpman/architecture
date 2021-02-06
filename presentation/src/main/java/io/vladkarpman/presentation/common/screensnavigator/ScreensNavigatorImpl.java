package io.vladkarpman.presentation.common.screensnavigator;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import io.vladkarpman.domain.entity.Movie;
import io.vladkarpman.presentation.details.DetailsGalleryFragment;
import io.vladkarpman.presentation.movies.MoviesFragment;

public class ScreensNavigatorImpl implements ScreensNavigator {

    @NonNull
    private final AppCompatActivity activity;

    @NonNull
    private final FragmentManager fragmentManager;

    private final int fragmentContainer;

    public ScreensNavigatorImpl(@NonNull AppCompatActivity activity,
                                @NonNull FragmentManager fragmentManager,
                                @IdRes int fragmentContainer) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.fragmentContainer = fragmentContainer;
    }

    @Override
    public void showMovies() {
        fragmentManager
                .beginTransaction()
                .add(fragmentContainer, new MoviesFragment())
                .commit();
    }

    @Override
    public void showMovieDetails(@NonNull Movie movie) {
        DetailsGalleryFragment detailsFragment = DetailsGalleryFragment.newInstance(movie);
        fragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .add(fragmentContainer, detailsFragment)
                .commit();
    }
}
