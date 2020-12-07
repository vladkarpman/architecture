package io.shelfy.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import io.shelfy.domain.entity.Movie;
import io.shelfy.presentation.screensnavigator.ScreensNavigator;
import io.shelfy.presentation.details.DetailsGalleryFragment;
import io.shelfy.presentation.movies.MoviesFragment;

public class ScreensNavigatorImpl implements ScreensNavigator {

    @NonNull
    private final AppCompatActivity activity;

    @NonNull
    private final FragmentManager fragmentManager;

    public ScreensNavigatorImpl(@NonNull AppCompatActivity activity,
                                @NonNull FragmentManager fragmentManager) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void showMovies() {
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, new MoviesFragment())
                .commit();
    }

    @Override
    public void showMovieDetails(@NonNull Movie movie) {
        DetailsGalleryFragment detailsFragment = DetailsGalleryFragment.newInstance(movie);
        fragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.fragment_container, detailsFragment)
                .commit();
    }
}
