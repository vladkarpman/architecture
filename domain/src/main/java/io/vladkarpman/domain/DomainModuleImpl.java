package io.vladkarpman.domain;


import io.reactivex.annotations.NonNull;
import io.vladkarpman.domain.repository.Repository;
import io.vladkarpman.domain.usecase.getmoviebyid.GetMovieByIdUseCase;
import io.vladkarpman.domain.usecase.getmoviebyid.GetMovieByIdUseCaseImpl;
import io.vladkarpman.domain.usecase.getmovies.GetPopularMoviesUseCase;
import io.vladkarpman.domain.usecase.getmovies.GetPopularMoviesUseCaseImpl;
import io.vladkarpman.domain.usecase.getmoviesbyquery.GetMoviesByQueryUseCase;
import io.vladkarpman.domain.usecase.getmoviesbyquery.GetMoviesByQueryUseCaseImpl;
import io.vladkarpman.domain.usecase.getmovietrailer.GetMovieTrailerUseCase;
import io.vladkarpman.domain.usecase.getmovietrailer.GetMovieTrailerUseCaseImpl;

public class DomainModuleImpl implements DomainModule {

    private GetPopularMoviesUseCase getPopularMoviesUseCase;
    private GetMoviesByQueryUseCase getMoviesByQueryUseCase;
    private GetMovieTrailerUseCase getMovieTrailerUseCase;
    private GetMovieByIdUseCase getMovieByIdUseCase;

    @NonNull
    private final Repository repository;

    public DomainModuleImpl(@NonNull Repository repository) {
        this.repository = repository;
    }

    @Override
    @NonNull
    public GetPopularMoviesUseCase provideGetPopularMoviesUseCase() {
        if (getPopularMoviesUseCase == null) {
            getPopularMoviesUseCase = new GetPopularMoviesUseCaseImpl(repository);
        }
        return getPopularMoviesUseCase;
    }

    @Override
    @NonNull
    public GetMoviesByQueryUseCase provideGetMoviesByQueryUseCase() {
        if (getMoviesByQueryUseCase == null) {
            getMoviesByQueryUseCase = new GetMoviesByQueryUseCaseImpl(repository);
        }
        return getMoviesByQueryUseCase;
    }

    @Override
    @NonNull
    public GetMovieTrailerUseCase provideGetMovieTrailerUseCase() {
        if (getMovieTrailerUseCase == null) {
            getMovieTrailerUseCase = new GetMovieTrailerUseCaseImpl(repository);
        }
        return getMovieTrailerUseCase;
    }

    @Override
    @NonNull
    public GetMovieByIdUseCase provideGetMovieByIdUseCase() {
        if (getMovieByIdUseCase == null) {
            getMovieByIdUseCase = new GetMovieByIdUseCaseImpl(repository);
        }
        return getMovieByIdUseCase;
    }
}
