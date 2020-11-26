package io.shelfy.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import io.shelfy.domain.DomainComponent;
import io.shelfy.domain.usecase.getmoviebyid.GetMovieByIdUseCase;
import io.shelfy.domain.usecase.getmovietrailer.GetMovieTrailerUseCase;
import io.shelfy.domain.usecase.getmoviesbyquery.GetMoviesByQueryUseCase;
import io.shelfy.domain.usecase.getmovies.GetPopularMoviesUseCase;
import io.shelfy.presentation.details.viewmodel.MovieDetailsViewModel;
import io.shelfy.presentation.movies.viewmodel.MoviesViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    private final GetPopularMoviesUseCase getPopularMoviesUseCase;

    @NonNull
    private final GetMoviesByQueryUseCase getMoviesByQueryUseCase;

    @NonNull
    private final GetMovieTrailerUseCase getMovieTrailerUseCase;

    @NonNull
    private final GetMovieByIdUseCase getMovieByIdUseCase;

    public ViewModelFactory(@NonNull DomainComponent domainComponent) {
        this.getPopularMoviesUseCase = domainComponent.provideGetPopularMoviesUseCase();
        this.getMoviesByQueryUseCase = domainComponent.provideGetMoviesByQueryUseCase();
        this.getMovieTrailerUseCase = domainComponent.provideGetMovieTrailerUseCase();
        this.getMovieByIdUseCase = domainComponent.provideGetMovieByIdUseCase();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(MoviesViewModel.class)) {
            return (T) new MoviesViewModel(getPopularMoviesUseCase, getMoviesByQueryUseCase);
        }
        if (modelClass.equals(MovieDetailsViewModel.class)) {
            return (T) new MovieDetailsViewModel(getMovieTrailerUseCase, getMovieByIdUseCase);
        }
        throw new RuntimeException();
    }
}
