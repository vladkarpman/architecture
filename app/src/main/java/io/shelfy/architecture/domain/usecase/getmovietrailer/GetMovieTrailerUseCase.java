package io.shelfy.architecture.domain.usecase.getmovietrailer;

import io.reactivex.rxjava3.core.Maybe;
import io.shelfy.architecture.domain.entity.MovieVideo;

public interface GetMovieTrailerUseCase {
    Maybe<MovieVideo> getTrailer(int movieId);
}
