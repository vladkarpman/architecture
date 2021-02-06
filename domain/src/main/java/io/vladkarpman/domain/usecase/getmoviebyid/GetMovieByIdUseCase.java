package io.vladkarpman.domain.usecase.getmoviebyid;

import io.reactivex.Maybe;
import io.vladkarpman.domain.entity.Movie;

public interface GetMovieByIdUseCase {
    Maybe<Movie> getMovie(int movieId);
}
