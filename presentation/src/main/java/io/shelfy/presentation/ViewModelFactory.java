package io.shelfy.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import io.shelfy.domain.DomainComponent;
import io.shelfy.domain.usecase.getmoviebyid.GetMovieByIdUseCase;
import io.shelfy.domain.usecase.getmovietrailer.GetMovieTrailerUseCase;
import io.shelfy.domain.usecase.getmoviesbyquery.GetMoviesByQueryUseCase;
import io.shelfy.domain.usecase.getmovies.GetPopularMoviesUseCase;
import io.shelfy.presentation.common.view.factory.BaseViewFactory;
import io.shelfy.presentation.common.viewmodel.BaseViewModelFactory;
import io.shelfy.presentation.details.viewmodel.MovieDetailsViewModel;
import io.shelfy.presentation.movies.viewmodel.MoviesViewModel;

public class ViewModelFactory extends BaseViewModelFactory {

    public ViewModelFactory(@NonNull DomainComponent domainComponent) {
        super(domainComponent);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(MoviesViewModel.class)) {
            return (T) new MoviesViewModel(
                    domainComponent.provideGetPopularMoviesUseCase(),
                    domainComponent.provideGetMoviesByQueryUseCase());
        }
        if (modelClass.equals(MovieDetailsViewModel.class)) {
            return (T) new MovieDetailsViewModel(
                    domainComponent.provideGetMovieTrailerUseCase(),
                    domainComponent.provideGetMovieByIdUseCase());
        }
        throw new RuntimeException();
    }
}
