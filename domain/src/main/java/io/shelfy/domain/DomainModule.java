package io.shelfy.domain;

import io.reactivex.annotations.NonNull;
import io.shelfy.domain.usecase.getmoviebyid.GetMovieByIdUseCase;
import io.shelfy.domain.usecase.getmovies.GetPopularMoviesUseCase;
import io.shelfy.domain.usecase.getmoviesbyquery.GetMoviesByQueryUseCase;
import io.shelfy.domain.usecase.getmovietrailer.GetMovieTrailerUseCase;

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
