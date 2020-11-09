package io.shelfy.architecture.domain.usecase.getmovietrailer;

import io.reactivex.Maybe;
import io.shelfy.architecture.domain.entity.MovieVideo;

public interface GetMovieTrailerUseCase {
    Maybe<MovieVideo> getTrailer(int movieId);
}
