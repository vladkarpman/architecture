package io.shelfy.architecture.domain.usecase.getmoviebyid;

import io.reactivex.Maybe;
import io.shelfy.architecture.domain.entity.Movie;

public interface GetMovieByIdUseCase {
    Maybe<Movie> getMovie(int movieId);
}
