package io.shelfy.architecture.presentation.movies;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import io.shelfy.architecture.R;
import io.shelfy.architecture.presentation.common.BaseActivity;
import io.shelfy.architecture.presentation.details.DetailsGalleryFragment;

class MoviesActivity extends BaseActivity {

    private MoviesAdapter moviesAdapter;

    private MoviesViewModel viewModel;

    private RecyclerView moviesRecyclerView;
    private ContentLoadingProgressBar progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        viewModel = activityComponent.provideViewModel(MoviesViewModel.class);

        moviesRecyclerView = findViewById(R.id.recycler);
        progress = findViewById(R.id.progress);

        moviesAdapter = new MoviesAdapter(this::showDetailsFragment);
        moviesRecyclerView.setAdapter(moviesAdapter);

        showProgress();
        initViewModelObservers();
    }

    private void initViewModelObservers() {
        viewModel.getMovies().observe(this, movies -> {
            hideProgress();
            moviesAdapter.setMovies(movies);
        });

        viewModel.getErrorMessages().observe(this, message -> {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@Nullable Menu menu) {
        if (menu != null) {
            getMenuInflater().inflate(R.menu.menu_movies, menu);
            MenuItem searchItem = menu.findItem(R.id.appSearchBar);
            if (searchItem != null && searchItem.getActionView() instanceof SearchView) {
                final SearchView searchView = (SearchView) searchItem.getActionView();
                searchView.setQueryHint("Search");
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        showMoviesStartWith(newText);
                        return true;
                    }
                });
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void showProgress() {
        progress.show();
        moviesRecyclerView.setVisibility(View.GONE);
    }

    private void hideProgress() {
        progress.hide();
        moviesRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showMoviesStartWith(String query) {
        viewModel.search(query);
    }

    private void showDetailsFragment(int selectedItemPosition) {
        DetailsGalleryFragment detailsFragment = DetailsGalleryFragment.newInstance(selectedItemPosition);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.container, detailsFragment)
                .commit();
    }
}
