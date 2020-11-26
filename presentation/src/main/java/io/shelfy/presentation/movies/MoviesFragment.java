package io.shelfy.presentation.movies;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import io.shelfy.presentation.R;
import io.shelfy.presentation.common.CommonFragment;
import io.shelfy.presentation.details.DetailsGalleryFragment;
import io.shelfy.presentation.movies.viewmodel.MoviesViewModel;

public class MoviesFragment extends CommonFragment {

    private MoviesAdapter moviesAdapter;

    private MoviesViewModel viewModel;

    private RecyclerView moviesRecyclerView;
    private ContentLoadingProgressBar progress;

    public MoviesFragment() {
        super(R.layout.fragment_movies_list);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = fragmentComponent.provideViewModel(MoviesViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        moviesRecyclerView = findViewById(R.id.recycler);
        progress = findViewById(R.id.progress);

        moviesAdapter = new MoviesAdapter(this::showDetailsFragment);
        moviesRecyclerView.setAdapter(moviesAdapter);

        showProgress();
        initViewModelObservers();
    }

    private void initViewModelObservers() {
        viewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
            hideProgress();
            moviesAdapter.setMovies(movies);
        });

        viewModel.getErrorMessages().observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(@Nullable Menu menu) {
//        if (menu != null) {
//            getMenuInflater().inflate(R.menu.menu_movies, menu);
//            MenuItem searchItem = menu.findItem(R.id.appSearchBar);
//            if (searchItem != null && searchItem.getActionView() instanceof SearchView) {
//                final SearchView searchView = (SearchView) searchItem.getActionView();
//                searchView.setQueryHint("Search");
//                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                    @Override
//                    public boolean onQueryTextSubmit(String query) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onQueryTextChange(String newText) {
//                        showMoviesStartWith(newText);
//                        return true;
//                    }
//                });
//            }
//        }
//        return super.onCreateOptionsMenu(menu);
//    }

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
        getParentFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.container, detailsFragment)
                .commit();
    }
}
