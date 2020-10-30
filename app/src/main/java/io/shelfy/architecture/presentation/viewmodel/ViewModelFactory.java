package io.shelfy.architecture.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import io.shelfy.architecture.domain.usecase.GetMovieTrailerUseCase;
import io.shelfy.architecture.domain.usecase.GetMoviesByQueryUseCase;
import io.shelfy.architecture.domain.usecase.GetPopularMoviesUseCase;
import io.shelfy.architecture.presentation.view.MovieDetailsViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    private final GetPopularMoviesUseCase getPopularMoviesUseCase;

    @NonNull
    private final GetMoviesByQueryUseCase getMoviesByQueryUseCase;

    @NonNull
    private final GetMovieTrailerUseCase getMovieTrailerUseCase;

    public ViewModelFactory(
            @NonNull GetPopularMoviesUseCase getPopularMoviesUseCase,
            @NonNull GetMoviesByQueryUseCase getMoviesByQueryUseCase,
            @NonNull GetMovieTrailerUseCase getMovieTrailerUseCase) {
        this.getPopularMoviesUseCase = getPopularMoviesUseCase;
        this.getMoviesByQueryUseCase = getMoviesByQueryUseCase;
        this.getMovieTrailerUseCase = getMovieTrailerUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(MoviesViewModel.class)) {
            return (T) new MoviesViewModel(getPopularMoviesUseCase, getMoviesByQueryUseCase);
        }
        if (modelClass.equals(MovieDetailsViewModel.class)) {
            return (T) new MovieDetailsViewModel(getMovieTrailerUseCase);
        }
        throw new RuntimeException();
    }
}
