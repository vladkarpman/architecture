package io.shelfy.presentation.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.shelfy.domain.entity.Movie;
import io.shelfy.presentation.BaseFragment;
import io.shelfy.presentation.R;
import io.shelfy.presentation.details.DetailsGalleryFragment;
import io.shelfy.presentation.movies.view.MoviesView;
import io.shelfy.presentation.movies.viewcontroller.MoviesViewController;
import io.shelfy.presentation.movies.viewmodel.MoviesViewModel;

public class MoviesFragment extends BaseFragment {

    private MoviesViewModel moviesViewModel;
    private MoviesView moviesView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        moviesViewModel = fragmentComponent.getPresentationModule().provideViewModel(MoviesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        moviesView = fragmentComponent.getPresentationModule().provideView(MoviesView.class, container);
        return moviesView.getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new MoviesViewController(
                this,
                moviesView,
                moviesViewModel,
                screenNavigator);
    }

    // TODO: 12/6/20 implement search
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

    private void showMoviesStartWith(String query) {
        moviesViewModel.search(query);
    }
}
