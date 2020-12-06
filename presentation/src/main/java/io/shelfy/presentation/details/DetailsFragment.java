package io.shelfy.presentation.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.bumptech.glide.Glide;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.shelfy.domain.entity.MovieVideo;
import io.shelfy.presentation.BaseFragment;
import io.shelfy.presentation.R;
import io.shelfy.domain.entity.Movie;
import io.shelfy.presentation.common.CommonFragment;
import io.shelfy.presentation.details.viewmodel.MovieDetailsViewModel;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieId = Objects.requireNonNull(getArguments()).getInt(MOVIE_ID);

        ivBackground = view.findViewById(R.id.ivBackground);
        ivPoster = view.findViewById(R.id.ivPoster);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvReleaseDate = view.findViewById(R.id.tvReleasedDate);
        tvOverview = view.findViewById(R.id.tvOverview);
        btnTrailer = view.findViewById(R.id.btnTrailer);

        viewModel = fragmentComponent.getPresentationModule().provideViewModel(MovieDetailsViewModel.class);

        onDestroyDisposables.add(viewModel.getMovie(movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie -> {
                    Glide.with(ivBackground)
                            .load(movie.getBackdropUrl())
                            .into(ivBackground);
                    Glide.with(ivPoster)
                            .load(movie.getPosterUrl())
                            .into(ivPoster);
                    tvTitle.setText(movie.getTitle());
                    tvReleaseDate.setText(movie.getReleaseDate());
                    tvOverview.setText(movie.getDescription());
                }));

        btnTrailer.setOnClickListener(v -> {
            onDestroyDisposables.add(viewModel.loadTrailer(movieId)
                    .map(MovieVideo::getVideoUrl)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::openMovieTrailer));
        });
    }

    @UiThread
    private void openMovieTrailer(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
