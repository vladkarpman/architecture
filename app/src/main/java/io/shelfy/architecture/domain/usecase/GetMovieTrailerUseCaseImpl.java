package io.shelfy.architecture.domain.usecase;

import androidx.annotation.NonNull;

import io.reactivex.rxjava3.core.Maybe;
import io.shelfy.architecture.domain.Repository;
import io.shelfy.architecture.domain.entity.MovieVideo;

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
