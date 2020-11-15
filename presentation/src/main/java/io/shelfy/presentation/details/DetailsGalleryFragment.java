package io.shelfy.presentation.details;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import io.shelfy.presentation.R;
import io.shelfy.presentation.common.BaseFragment;
import io.shelfy.presentation.movies.viewmodel.MoviesViewModel;

public class DetailsGalleryFragment extends BaseFragment {

    private static final String ARGS_MOVIE_POSITION = "ARGS_MOVIE_POSITION";

    private MoviesViewModel viewModel;
    private ViewPager moviesPager;
    private DetailsFragmentAdapter detailsFragmentAdapter;

    public DetailsGalleryFragment() {
        super(R.layout.fragment_movies_gallery);
    }

    public static DetailsGalleryFragment newInstance(int position) {
        DetailsGalleryFragment galleryFragment = new DetailsGalleryFragment();
        final Bundle arguments = new Bundle();
        arguments.putInt(ARGS_MOVIE_POSITION, position);
        galleryFragment.setArguments(arguments);
        return galleryFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviesPager = view.findViewById(R.id.vp_pager);
        detailsFragmentAdapter = new DetailsFragmentAdapter(getChildFragmentManager());
        moviesPager.setAdapter(detailsFragmentAdapter);

        int position = -1;
        if (savedInstanceState != null) {
            position = savedInstanceState.getInt(ARGS_MOVIE_POSITION);
        }

        moviesPager.setCurrentItem(position);

        viewModel = fragmentComponent.provideViewModel(MoviesViewModel.class);
        viewModel.getMovies().observe(getViewLifecycleOwner(), movies -> detailsFragmentAdapter.setData(movies));
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
