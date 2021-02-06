package io.vladkarpman.domain.usecase.getmoviebyid;


import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;
import io.vladkarpman.domain.repository.Repository;
import io.vladkarpman.domain.entity.Movie;

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
