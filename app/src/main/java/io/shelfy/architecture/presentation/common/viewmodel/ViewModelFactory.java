package io.shelfy.architecture.presentation.common.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import io.shelfy.architecture.domain.usecase.GetMovieByIdUseCase;
import io.shelfy.architecture.domain.usecase.GetMovieTrailerUseCase;
import io.shelfy.architecture.domain.usecase.GetMoviesByQueryUseCase;
import io.shelfy.architecture.domain.usecase.GetPopularMoviesUseCase;
import io.shelfy.architecture.presentation.details.MovieDetailsViewModel;
import io.shelfy.architecture.presentation.movies.MoviesViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    private final GetPopularMoviesUseCase getPopularMoviesUseCase;

    @NonNull
    private final GetMoviesByQueryUseCase getMoviesByQueryUseCase;

    @NonNull
    private final GetMovieTrailerUseCase getMovieTrailerUseCase;

    @NonNull
    private final GetMovieByIdUseCase getMovieByIdUseCase;

    public ViewModelFactory(
            @NonNull GetPopularMoviesUseCase getPopularMoviesUseCase,
            @NonNull GetMoviesByQueryUseCase getMoviesByQueryUseCase,
            @NonNull GetMovieTrailerUseCase getMovieTrailerUseCase,
            @NonNull GetMovieByIdUseCase getMovieByIdUseCase) {
        this.getPopularMoviesUseCase = getPopularMoviesUseCase;
        this.getMoviesByQueryUseCase = getMoviesByQueryUseCase;
        this.getMovieTrailerUseCase = getMovieTrailerUseCase;
        this.getMovieByIdUseCase = getMovieByIdUseCase;
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
