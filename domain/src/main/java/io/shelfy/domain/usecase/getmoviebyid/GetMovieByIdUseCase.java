package io.shelfy.domain.usecase.getmoviebyid;

import io.reactivex.Maybe;
import io.shelfy.domain.entity.Movie;

public interface GetMovieByIdUseCase {
    Maybe<Movie> getMovie(int movieId);
}
