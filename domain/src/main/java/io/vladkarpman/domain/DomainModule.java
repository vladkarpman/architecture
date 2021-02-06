package io.vladkarpman.domain;

import io.reactivex.annotations.NonNull;
import io.vladkarpman.domain.usecase.getmoviebyid.GetMovieByIdUseCase;
import io.vladkarpman.domain.usecase.getmovies.GetPopularMoviesUseCase;
import io.vladkarpman.domain.usecase.getmoviesbyquery.GetMoviesByQueryUseCase;
import io.vladkarpman.domain.usecase.getmovietrailer.GetMovieTrailerUseCase;

public interface DomainModule {
    @NonNull
    GetPopularMoviesUseCase provideGetPopularMoviesUseCase();

    @NonNull
    GetMoviesByQueryUseCase provideGetMoviesByQueryUseCase();

    @NonNull
    GetMovieTrailerUseCase provideGetMovieTrailerUseCase();

    @NonNull
    GetMovieByIdUseCase provideGetMovieByIdUseCase();
}
