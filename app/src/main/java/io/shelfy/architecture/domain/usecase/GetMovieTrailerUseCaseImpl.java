package io.shelfy.architecture.domain.usecase;

import androidx.annotation.NonNull;

import io.reactivex.rxjava3.core.Maybe;
import io.shelfy.architecture.domain.MoviesRepository;
import io.shelfy.architecture.domain.entity.MovieVideo;

public class GetMovieTrailerUseCaseImpl implements GetMovieTrailerUseCase{
    @NonNull
    private final MoviesRepository moviesRepository;

    public GetMovieTrailerUseCaseImpl(@NonNull MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Override
    public Maybe<MovieVideo> getTrailer(int movieId) {
        return moviesRepository.getMovieTrailer(movieId);
    }
}
