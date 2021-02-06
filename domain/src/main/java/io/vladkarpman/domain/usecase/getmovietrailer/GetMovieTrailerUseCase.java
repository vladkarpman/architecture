package io.vladkarpman.domain.usecase.getmovietrailer;

import io.reactivex.Maybe;
import io.vladkarpman.domain.entity.MovieVideo;

public interface GetMovieTrailerUseCase {
    Maybe<MovieVideo> getTrailer(int movieId);
}
