package io.shelfy.architecture.domain.usecase.getmovietrailer;

import androidx.annotation.NonNull;

import io.reactivex.Maybe;
import io.shelfy.architecture.data.repository.Repository;
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
