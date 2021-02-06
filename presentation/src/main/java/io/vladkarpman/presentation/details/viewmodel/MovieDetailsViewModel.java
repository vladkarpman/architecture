package io.vladkarpman.presentation.details.viewmodel;

import androidx.annotation.NonNull;

import io.reactivex.Maybe;
import io.vladkarpman.domain.entity.Movie;
import io.vladkarpman.domain.entity.MovieVideo;
import io.vladkarpman.domain.usecase.getmoviebyid.GetMovieByIdUseCase;
import io.vladkarpman.domain.usecase.getmovietrailer.GetMovieTrailerUseCase;
import io.vladkarpman.presentation.common.viewmodel.BaseViewModel;

public class MovieDetailsViewModel extends BaseViewModel {

    @NonNull
    private final GetMovieTrailerUseCase getMovieTrailerUseCase;

    @NonNull
    private final GetMovieByIdUseCase getMovieByIdUseCase;

    public MovieDetailsViewModel(@NonNull GetMovieTrailerUseCase getMovieTrailerUseCase,
                                 @NonNull GetMovieByIdUseCase getMovieByIdUseCase) {
        this.getMovieTrailerUseCase = getMovieTrailerUseCase;
        this.getMovieByIdUseCase = getMovieByIdUseCase;
    }

    public Maybe<MovieVideo> loadTrailer(int movieId) {
        return getMovieTrailerUseCase.getTrailer(movieId);
    }

    public Maybe<Movie> getMovie(int movieId) {
        return getMovieByIdUseCase.getMovie(movieId);
    }
}
