package io.shelfy.domain.usecase.getmovietrailer;

import androidx.annotation.NonNull;

import io.reactivex.Maybe;
import io.shelfy.domain.Repository;
import io.shelfy.domain.entity.MovieVideo;

public class GetMovieTrailerUseCaseImpl implements GetMovieTrailerUseCase {

    @NonNull
    private final Repository repository;

    public GetMovieTrailerUseCaseImpl(@NonNull Repository repository) {
        this.repository = repository;
    }

    @Override
    public Maybe<MovieVideo> getTrailer(int movieId) {
        return repository.getMovieTrailer(movieId);
    }
}
