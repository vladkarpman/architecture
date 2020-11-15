package io.shelfy.domain.usecase.getmovietrailer;

import io.reactivex.Maybe;
import io.shelfy.domain.entity.MovieVideo;

public interface GetMovieTrailerUseCase {
    Maybe<MovieVideo> getTrailer(int movieId);
}
