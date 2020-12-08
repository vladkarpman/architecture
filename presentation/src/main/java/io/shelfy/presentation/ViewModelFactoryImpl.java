package io.shelfy.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import io.shelfy.domain.DomainModule;
import io.shelfy.presentation.common.viewmodel.BaseViewModelFactory;
import io.shelfy.presentation.details.viewmodel.MovieDetailsViewModel;
import io.shelfy.presentation.movies.viewmodel.MoviesViewModel;
import io.shelfy.presentation.movies.viewmodel.MoviesViewModelImpl;

public class ViewModelFactoryImpl extends BaseViewModelFactory {

    public ViewModelFactoryImpl(@NonNull DomainModule domainModule) {
        super(domainModule);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (MoviesViewModel.class.isAssignableFrom(modelClass)) {
            return (T) new MoviesViewModelImpl(
                    domainModule.provideGetPopularMoviesUseCase(),
                    domainModule.provideGetMoviesByQueryUseCase());
        }
        if (MovieDetailsViewModel.class.isAssignableFrom(modelClass)) {
            return (T) new MovieDetailsViewModel(
                    domainModule.provideGetMovieTrailerUseCase(),
                    domainModule.provideGetMovieByIdUseCase());
        }
        throw new RuntimeException();
    }

    @NonNull
    @Override
    public <T extends ViewModel> Class<T> getImplementationClass(Class<?> modelClass) {
        if (modelClass.equals(MoviesViewModel.class)) {
            return (Class<T>) MoviesViewModelImpl.class;
        }
        if (modelClass.equals(MovieDetailsViewModel.class)) {
            return (Class<T>) MovieDetailsViewModel.class;
        }
        throw new RuntimeException();
    }
}
