package io.shelfy.domain.usecase.getmoviebyid;

import androidx.annotation.NonNull;


import io.reactivex.Maybe;
import io.shelfy.domain.Repository;
import io.shelfy.domain.entity.Movie;

public class GetMovieByIdUseCaseImpl implements GetMovieByIdUseCase {

    @NonNull
    private final Repository repository;

    public GetMovieByIdUseCaseImpl(@NonNull Repository repository) {
        this.repository = repository;
    }

    @Override
    public Maybe<Movie> getMovie(int movieId) {
        return repository.getMovieById(movieId);
    }
}
