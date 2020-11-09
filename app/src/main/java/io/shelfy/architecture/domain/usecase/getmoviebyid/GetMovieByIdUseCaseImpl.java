package io.shelfy.architecture.domain.usecase.getmoviebyid;

import androidx.annotation.NonNull;


import io.reactivex.Maybe;
import io.shelfy.architecture.data.repository.Repository;
import io.shelfy.architecture.domain.entity.Movie;

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
