package io.shelfy.domain;

import androidx.annotation.NonNull;

import io.shelfy.domain.usecase.getmoviebyid.GetMovieByIdUseCase;
import io.shelfy.domain.usecase.getmoviebyid.GetMovieByIdUseCaseImpl;
import io.shelfy.domain.usecase.getmovies.GetPopularMoviesUseCase;
import io.shelfy.domain.usecase.getmovies.GetPopularMoviesUseCaseImpl;
import io.shelfy.domain.usecase.getmoviesbyquery.GetMoviesByQueryUseCase;
import io.shelfy.domain.usecase.getmoviesbyquery.GetMoviesByQueryUseCaseImpl;
import io.shelfy.domain.usecase.getmovietrailer.GetMovieTrailerUseCase;
import io.shelfy.domain.usecase.getmovietrailer.GetMovieTrailerUseCaseImpl;

public class DomainComponent {

    private GetPopularMoviesUseCase getPopularMoviesUseCase;
    private GetMoviesByQueryUseCase getMoviesByQueryUseCase;
    private GetMovieTrailerUseCase getMovieTrailerUseCase;
    private GetMovieByIdUseCase getMovieByIdUseCase;

    @NonNull
    private final Repository repository;

    public DomainComponent(@NonNull Repository repository) {
        this.repository = repository;
    }

    @NonNull
    public GetPopularMoviesUseCase provideGetPopularMoviesUseCase() {
        if (getPopularMoviesUseCase == null) {
            getPopularMoviesUseCase = new GetPopularMoviesUseCaseImpl(repository);
        }
        return getPopularMoviesUseCase;
    }

    @NonNull
    public GetMoviesByQueryUseCase provideGetMoviesByQueryUseCase() {
        if (getMoviesByQueryUseCase == null) {
            getMoviesByQueryUseCase = new GetMoviesByQueryUseCaseImpl(repository);
        }
        return getMoviesByQueryUseCase;
    }

    @NonNull
    public GetMovieTrailerUseCase provideGetMovieTrailerUseCase() {
        if (getMovieTrailerUseCase == null) {
            getMovieTrailerUseCase = new GetMovieTrailerUseCaseImpl(repository);
        }
        return getMovieTrailerUseCase;
    }

    @NonNull
    public GetMovieByIdUseCase provideGetMovieByIdUseCase() {
        if (getMovieByIdUseCase == null) {
            getMovieByIdUseCase = new GetMovieByIdUseCaseImpl(repository);
        }
        return getMovieByIdUseCase;
    }
}
