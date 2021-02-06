package io.vladkarpman.domain.usecase.getmovietrailer;


import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;
import io.vladkarpman.domain.repository.Repository;
import io.vladkarpman.domain.entity.MovieVideo;

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
