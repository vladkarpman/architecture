package io.shelfy.architecture.domain.usecase;

import io.reactivex.rxjava3.core.Maybe;
import io.shelfy.architecture.domain.entity.Movie;

public interface GetMovieByIdUseCase {
    Maybe<Movie> getMovie(int movieId);
}
