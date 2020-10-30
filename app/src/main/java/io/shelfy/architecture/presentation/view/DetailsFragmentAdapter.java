package io.shelfy.architecture.presentation.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.shelfy.architecture.domain.entity.Movie;

public class DetailsFragmentAdapter extends FragmentStatePagerAdapter {

    @NonNull
    private final List<Movie> movieList;

    public DetailsFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull List<Movie> movieList) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.movieList = movieList;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        return DetailsFragment.newInstance(moviesList.get(position));
    }

    @Override
    public int getCount() {
        return movieList.size();
    }
}
