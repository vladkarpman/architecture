package io.shelfy.presentation.details;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.util.concurrent.atomic.AtomicInteger;

import io.shelfy.domain.entity.Movie;
import io.shelfy.presentation.BaseFragment;
import io.shelfy.presentation.R;
import io.shelfy.presentation.movies.viewmodel.MoviesViewModel;
import io.shelfy.presentation.movies.viewmodel.MoviesViewModelImpl;

public class DetailsGalleryFragment extends BaseFragment {

    private static final String ARGS_MOVIE_POSITION = "ARGS_MOVIE_POSITION";
    private static final String ARGS_MOVIE_ID = "ARGS_MOVIE_ID";

    private MoviesViewModel viewModel;
    private ViewPager moviesPager;
    private DetailsFragmentAdapter detailsFragmentAdapter;

    public DetailsGalleryFragment() {
        super(R.layout.fragment_movies_gallery);
    }

    public static DetailsGalleryFragment newInstance(Movie movie) {
        DetailsGalleryFragment galleryFragment = new DetailsGalleryFragment();
        final Bundle arguments = new Bundle();
        arguments.putInt(ARGS_MOVIE_ID, movie.getId());
        galleryFragment.setArguments(arguments);
        return galleryFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviesPager = view.findViewById(R.id.vp_pager);
        detailsFragmentAdapter = new DetailsFragmentAdapter(getChildFragmentManager());
        moviesPager.setAdapter(detailsFragmentAdapter);

        final AtomicInteger currentPosition = new AtomicInteger();
        final AtomicInteger movieId = new AtomicInteger();
        final Bundle arguments = savedInstanceState != null ? savedInstanceState : getArguments();
        if (arguments != null) {
            movieId.set(arguments.getInt(ARGS_MOVIE_ID));
            currentPosition.set(arguments.getInt(ARGS_MOVIE_POSITION));
        }

        viewModel = fragmentComponent.getPresentationModule().provideViewModel(MoviesViewModel.class);
        viewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
            detailsFragmentAdapter.setData(movies);
            int currentItem = -1;
            int currentMovieId = movieId.get();
            if (currentMovieId != -1) {
                int index = 0;
                for (Movie movie : movies) {
                    if (movie.getId() == currentMovieId) {
                        currentItem = index;
                        break;
                    }
                    index++;
                }
            } else {
                currentItem = currentPosition.get();
            }
            if (currentItem != -1) {
                moviesPager.setCurrentItem(currentItem, false);
            }
        });
        viewModel.getErrorMessages().observe(
                getViewLifecycleOwner(),
                message -> Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(ARGS_MOVIE_POSITION, moviesPager.getCurrentItem());
        super.onSaveInstanceState(outState);
    }
}
