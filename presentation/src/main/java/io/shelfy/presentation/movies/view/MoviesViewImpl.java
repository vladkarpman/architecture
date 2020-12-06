package io.shelfy.presentation.movies.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.shelfy.domain.entity.Movie;
import io.shelfy.presentation.R;
import io.shelfy.presentation.common.view.BaseView;

public class MoviesViewImpl extends BaseView implements MoviesView {

    private final MoviesAdapter moviesAdapter;
    private final RecyclerView moviesRecyclerView;
    private final ContentLoadingProgressBar progress;
    private final PublishSubject<Movie> movieClicks = PublishSubject.create();

    public MoviesViewImpl(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup parent) {
        super(layoutInflater, parent);

        moviesRecyclerView = findViewById(R.id.recycler);
        progress = findViewById(R.id.progress);

        moviesAdapter = new MoviesAdapter(movieClicks::onNext);
        moviesRecyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public Observable<Movie> onMovieClicked() {
        return movieClicks.hide();
    }

    @Override
    public void showProgress() {
        progress.show();
        moviesRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progress.hide();
        moviesRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMovies(@NonNull List<Movie> movies) {
        moviesAdapter.setMovies(movies);
    }

    @Override
    public void showError(@NonNull String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movies_list;
    }
}
