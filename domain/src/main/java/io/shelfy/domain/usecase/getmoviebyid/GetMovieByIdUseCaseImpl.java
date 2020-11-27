package io.shelfy.domain.usecase.getmoviebyid;


import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;
import io.shelfy.domain.repository.Repository;
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
