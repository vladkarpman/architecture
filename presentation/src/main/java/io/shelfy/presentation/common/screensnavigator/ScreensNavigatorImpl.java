package io.shelfy.presentation.common.screensnavigator;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import io.shelfy.domain.entity.Movie;
import io.shelfy.presentation.R;
import io.shelfy.presentation.common.screensnavigator.ScreensNavigator;
import io.shelfy.presentation.details.DetailsGalleryFragment;
import io.shelfy.presentation.movies.MoviesFragment;

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
