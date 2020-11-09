package io.shelfy.architecture.presentation.details;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.presentation.details.DetailsFragment;

public class DetailsFragmentAdapter extends FragmentStatePagerAdapter {

    @NonNull
    private final List<Movie> moviesList = new ArrayList<>();

    public DetailsFragmentAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @UiThread
    public void setData(List<Movie> moviesList) {
        this.moviesList.clear();
        this.moviesList.addAll(moviesList);
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        return DetailsFragment.newInstance(moviesList.get(position));
    }

    @Override
    public int getCount() {
        return moviesList.size();
    }
}
