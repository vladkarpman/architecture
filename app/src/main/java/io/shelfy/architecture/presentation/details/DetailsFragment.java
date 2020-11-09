package io.shelfy.architecture.presentation.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.Objects;

import io.shelfy.architecture.R;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.presentation.common.BaseFragment;

public class DetailsFragment extends BaseFragment {

    private static final String MOVIE_ID = "movie_id";

    private int movieId = -1;
    private View btnTrailer;

    private MovieDetailsViewModel viewModel;
    private ImageView ivBackground;
    private ImageView ivPoster;
    private TextView tvTitle;
    private TextView tvReleaseDate;
    private TextView tvOverview;

    public static DetailsFragment newInstance(Movie movie) {
        final DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MOVIE_ID, movie.getId());
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }

    public DetailsFragment() {
        super(R.layout.fragment_movie_details);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieId = Objects.requireNonNull(getArguments()).getInt(MOVIE_ID);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivBackground = view.findViewById(R.id.ivBackground);
        ivPoster = view.findViewById(R.id.ivPoster);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvReleaseDate = view.findViewById(R.id.tvReleasedDate);
        tvOverview = view.findViewById(R.id.tvOverview);
        btnTrailer = view.findViewById(R.id.btnTrailer);

        viewModel = fragmentComponent.provideViewModel(MovieDetailsViewModel.class);

        initViewModelObservers();

        btnTrailer.setOnClickListener(v -> viewModel.loadTrailer(movieId).observe(
                getViewLifecycleOwner(),
                movieVideo -> openMovieTrailer(movieVideo.getVideoUrl())));
    }

    private void openMovieTrailer(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private void initViewModelObservers() {
        viewModel.getMovie(movieId).observe(getViewLifecycleOwner(), movie -> {
            Glide.with(ivBackground)
                    .load(movie.getBackdropUrl())
                    .into(ivBackground);
            Glide.with(ivPoster)
                    .load(movie.getPosterUrl())
                    .into(ivPoster);
            tvTitle.setText(movie.getTitle());
            tvReleaseDate.setText(movie.getReleaseDate());
            tvOverview.setText(movie.getDescription());
        });
    }
}
