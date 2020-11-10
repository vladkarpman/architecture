package io.shelfy.architecture.presentation.details.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.domain.entity.MovieVideo;
import io.shelfy.architecture.domain.usecase.getmoviebyid.GetMovieByIdUseCase;
import io.shelfy.architecture.domain.usecase.getmovietrailer.GetMovieTrailerUseCase;

import static androidx.lifecycle.LiveDataReactiveStreams.fromPublisher;

public class MovieDetailsViewModel extends ViewModel {

    @NonNull
    private final GetMovieTrailerUseCase getMovieTrailerUseCase;

    @NonNull
    private final GetMovieByIdUseCase getMovieByIdUseCase;

    public MovieDetailsViewModel(@NonNull GetMovieTrailerUseCase getMovieTrailerUseCase,
                                 @NonNull GetMovieByIdUseCase getMovieByIdUseCase) {
        this.getMovieTrailerUseCase = getMovieTrailerUseCase;
        this.getMovieByIdUseCase = getMovieByIdUseCase;
    }

    public LiveData<MovieVideo> loadTrailer(int movieId) {
        return fromPublisher(getMovieTrailerUseCase.getTrailer(movieId).toFlowable());
    }

    public LiveData<Movie> getMovie(int movieId) {
        return fromPublisher(getMovieByIdUseCase.getMovie(movieId).toFlowable());
    }

}
